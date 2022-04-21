import { Button, Form, Input, InputNumber } from 'antd';
import { DollarOutlined, LockOutlined, UserOutlined } from '@ant-design/icons';
import React, { useCallback } from 'react';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { DFAInfoForCreateDTO } from '@src/generated/backend';
import NotificationService from '@src/services/NotificationService';

function getRequiredRule(message: string): [{ required: boolean; message: string }] {
  return [{ required: true, message }];
}

const formItemLayout = {
  labelCol: {
    sm: { span: 8 },
  },
  wrapperCol: {
    sm: { span: 16 },
  },
};

const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 16,
      offset: 8,
    },
  },
};

export function CreateDfa() {
  const { dfaControllerApi } = useHttpClient();
  const creteDfa = useCallback(
    (info: DFAInfoForCreateDTO) => {
      dfaControllerApi
        .createDFA({ dFAInfoForCreateDTO: info })
        .then(() => NotificationService.reportSuccess(`Заявка на создание ЦФА с именем ${info.name} учпешно подана!`));
    },
    [dfaControllerApi],
  );
  return (
    <Form
      {...formItemLayout}
      style={{ padding: '10px' }}
      size="large"
      onFinish={(info: DFAInfoForCreateDTO) => creteDfa(info)}
    >
      <Form.Item name="name" label="Имя ЦФА" rules={getRequiredRule('Имя не может быть пустым!')}>
        <Input placeholder="Введите имя" prefix={<UserOutlined />} />
      </Form.Item>
      <Form.Item
        name="symbol"
        label="Условное обозначение"
        rules={getRequiredRule('Обозначение не может быть пустым!')}
      >
        <Input placeholder="Введите обозначение" prefix={<LockOutlined />} />
      </Form.Item>
      <Form.Item
        name="initialSupply"
        label="Начальное число активов"
        rules={getRequiredRule('Число активов не может быть пустым!')}
      >
        <InputNumber
          style={{ width: '100%' }}
          size="small"
          placeholder="Число активов"
          min={0}
          precision={0}
          prefix={<DollarOutlined />}
        />
      </Form.Item>
      <Form.Item {...tailFormItemLayout} name="submit">
        <Button type="primary" htmlType="submit">
          Создать ЦФА
        </Button>
      </Form.Item>
    </Form>
  );
}
