import React from 'react';
import { Button, DatePicker, Form, InputNumber, Radio, Select } from 'antd';
import { DollarOutlined } from '@ant-design/icons';
import { ExchangeRequestDTOTypeEnum } from '@src/generated/backend';
import { ExchangeRequestFormInfo, useCreateRequestForm } from '@src/components/requests/createRequestForm/hook';
import moment, { Moment } from 'moment';

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
  const { dfaToBuy, dfaToSell, balances, creteRequest } = useCreateRequestForm();

  return (
    <Form
      {...formItemLayout}
      style={{ padding: '10px' }}
      size="large"
      onFinish={(info: ExchangeRequestFormInfo) => creteRequest(info)}
    >
      <Form.Item name="type" label="Тип заявки" rules={[{ required: true, message: 'Выберете тип!' }]}>
        <Radio.Group>
          <Radio.Button key="DIVISIBLE" value={ExchangeRequestDTOTypeEnum.DIVISIBLE}>
            Делимая
          </Radio.Button>
          <Radio.Button key="INDIVISIBLE" value={ExchangeRequestDTOTypeEnum.INDIVISIBLE}>
            Неделимая
          </Radio.Button>
        </Radio.Group>
      </Form.Item>
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
      <Form.Item
        name="endTime"
        label="Время действия"
        rules={[getRequiredRule('Время действия не может быть пустым!')]}
      >
        <DatePicker showTime disabledDate={(currentDate: Moment) => currentDate.isBefore(moment(new Date()))} />
      </Form.Item>
      <Form.Item {...tailFormItemLayout} name="submit">
        <Button type="primary" htmlType="submit">
          Создать заявку на обмен
        </Button>
      </Form.Item>
    </Form>
  );
}
