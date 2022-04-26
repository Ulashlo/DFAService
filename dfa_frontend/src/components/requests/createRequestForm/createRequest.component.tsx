import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import React, { useCallback, useMemo } from 'react';
import NotificationService from '@src/services/NotificationService';
import { Button, Form, InputNumber, Select } from 'antd';
import { DollarOutlined } from '@ant-design/icons';
import { useDfasInfo } from '@src/redux/hooks/dfas';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { useBalancesInfo } from '@src/redux/hooks/balances';
import { useAutoUpdateBalances } from '@src/hooks/useAutoUpdateBalances.hook';
import { ExchangeRequestDTO } from '@src/generated/backend';
import { DELAY } from '@src/utils/constraints';

function getRequiredRule(message: string): { required: boolean; message: string } {
  return { required: true, message };
}

const formItemLayout = {
  labelCol: {
    sm: { span: 10 },
  },
  wrapperCol: {
    sm: { span: 14 },
  },
};

const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 14,
      offset: 10,
    },
  },
};

export function CreateRequest() {
  useAutoUpdateDfas();
  useAutoUpdateBalances(DELAY);
  const { exchangerControllerApi } = useHttpClient();
  const creteRequest = useCallback(
    (info: ExchangeRequestDTO) => {
      exchangerControllerApi
        .addExchange({ exchangeRequestDTO: info })
        .then(() => NotificationService.reportSuccess(`Запрос успешно создан!`));
    },
    [exchangerControllerApi],
  );
  const dfas = useDfasInfo();
  const balances = useBalancesInfo();
  const dfaToBuy = useMemo(() => dfas, [dfas]);
  const dfaToSell = useMemo(
    () =>
      dfas.filter((dfa) => {
        const balance = balances.find((b) => b.address === dfa.address)?.balance ?? 0;
        return balance > 0;
      }),
    [dfas, balances],
  );
  return (
    <Form
      {...formItemLayout}
      style={{ padding: '10px' }}
      size="large"
      onFinish={(info: ExchangeRequestDTO) => creteRequest(info)}
    >
      <Form.Item name="dfaToGet" label="ЦФА на покупку" rules={[getRequiredRule('Выберете ЦФА!')]}>
        <Select placeholder="Выберете ЦФА">
          {dfaToBuy.map((dfa) => (
            <Select.Option value={dfa.address}>{`${dfa.name} (${dfa.symbol})`}</Select.Option>
          ))}
        </Select>
      </Form.Item>
      <Form.Item
        name="amountToGet"
        label="Колличество ЦФА на покупку"
        rules={[getRequiredRule('Число активов не может быть пустым!')]}
      >
        <InputNumber
          style={{ width: '100%' }}
          size="small"
          placeholder="Введите число активов"
          min={0}
          precision={0}
          prefix={<DollarOutlined />}
        />
      </Form.Item>
      <Form.Item
        name="dfaToGive"
        label="ЦФА на продажу"
        dependencies={['dfaToGet']}
        rules={[
          getRequiredRule('Выберете ЦФА!'),
          ({ getFieldValue }) => ({
            validator(_, value) {
              const address = getFieldValue('dfaToGet');

              if (!value || !address || address.toLowerCase() !== value.toLowerCase()) {
                return Promise.resolve();
              }
              return Promise.reject(new Error('ЦФА на покупку и продаже должны отличаться!'));
            },
          }),
        ]}
      >
        <Select placeholder="Выберете ЦФА">
          {dfaToSell.map((dfa) => (
            <Select.Option value={dfa.address}>{`${dfa.name} (${dfa.symbol})`}</Select.Option>
          ))}
        </Select>
      </Form.Item>
      <Form.Item
        name="amountToGive"
        label="Колличество ЦФА на продажу"
        rules={[
          getRequiredRule('Число активов не может быть пустым!'),
          ({ getFieldValue }) => ({
            validator(_, value) {
              const address = getFieldValue('dfaToGive');
              const balance = balances.find((b) => b.address === address)?.balance;
              if (!balance) {
                return Promise.reject(new Error('Нет средств!'));
              }
              if (!value || value <= balance) {
                return Promise.resolve();
              }
              return Promise.reject(new Error('Не досаточно средств!'));
            },
          }),
        ]}
      >
        <InputNumber
          style={{ width: '100%' }}
          size="small"
          placeholder="Введите число активов"
          min={0}
          precision={0}
          prefix={<DollarOutlined />}
        />
      </Form.Item>
      <Form.Item {...tailFormItemLayout} name="submit">
        <Button type="primary" htmlType="submit">
          Создать заявку на обмен
        </Button>
      </Form.Item>
    </Form>
  );
}
