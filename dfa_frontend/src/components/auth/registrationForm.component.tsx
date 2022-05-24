import { useRegistrationForm } from '@src/components/auth/hook';
import { Button, Col, Form, Input, Row } from 'antd';
import React from 'react';
import { UserInfoForCreateDTO } from '@src/generated/backend';
import { PASSWORD_MAX_SIZE, PASSWORD_MIN_SIZE, USERNAME_MAX_SIZE, USERNAME_MIN_SIZE } from '@src/utils/constraints';
import { useHistory } from 'react-router-dom';
import { pages } from '@src/CustomRouter';
import { isAddress } from 'web3-utils';

export interface RegisterFormProps {
  goToLogin: () => void;
}

function getRequiredRule(message: string): { required: boolean; message: string } {
  return { required: true, message };
}

function getMinLengthRule(message: string, min: number): { message: string; min: number } {
  return { message, min };
}

function getMaxLengthRule(message: string, max: number): { message: string; max: number } {
  return { message, max };
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

export function RegistrationForm({ goToLogin }: RegisterFormProps) {
  const { registration } = useRegistrationForm();
  const history = useHistory();

  return (
    <Form
      {...formItemLayout}
      style={{ padding: '10px' }}
      size="large"
      onFinish={(userInfoForCreate: UserInfoForCreateDTO) => {
        registration(userInfoForCreate);
        history.push(pages.dfa.uri);
      }}
    >
      <Form.Item
        name="username"
        label="Имя пользователя"
        rules={[
          getRequiredRule('Имя пользователя не может быть пустым!'),
          getMinLengthRule(`Ник должен иметь ${USERNAME_MIN_SIZE} или более символов!`, USERNAME_MIN_SIZE),
          getMaxLengthRule(`Ник должен иметь ${USERNAME_MAX_SIZE} или менее символов!`, USERNAME_MAX_SIZE),
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="password"
        label="Пароль"
        rules={[
          getRequiredRule('Пароль не может быть пустым!'),
          getMinLengthRule(`Пароль должен иметь ${PASSWORD_MIN_SIZE} или более символов!`, PASSWORD_MIN_SIZE),
          getMaxLengthRule(`Пароль должен иметь ${PASSWORD_MAX_SIZE} или менее символов!`, PASSWORD_MAX_SIZE),
        ]}
      >
        <Input.Password />
      </Form.Item>
      <Form.Item
        name="passwordRepeat"
        label="Повторите пароль"
        dependencies={['password']}
        rules={[
          getRequiredRule('Пожалуйста, повторите пароль!'),
          ({ getFieldValue }) => ({
            validator(_, value) {
              if (!value || getFieldValue('password') === value) {
                return Promise.resolve();
              }
              return Promise.reject(new Error('Пароли не совпадают!'));
            },
          }),
        ]}
      >
        <Input.Password />
      </Form.Item>
      <Form.Item name="email" label="Email">
        <Input />
      </Form.Item>
      <Form.Item
        name="address"
        label="Адресс ethereum"
        rules={[
          () => ({
            validator(_, value) {
              if (!value || value.length === 0 || isAddress(value)) {
                return Promise.resolve();
              }
              return Promise.reject(new Error('Некорректный адрес!'));
            },
          }),
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="privateKey"
        label="Приватный ключ ethereum"
        dependencies={['address']}
        rules={[
          ({ getFieldValue }) => ({
            validator(_, value) {
              const keyExist = value && value.length > 0;
              const addressExist = getFieldValue('address') && getFieldValue('address').length > 0;
              if ((keyExist && addressExist) || (!keyExist && !addressExist)) {
                return Promise.resolve();
              }
              return Promise.reject(new Error('Укажите и адрес и приватный ключ!'));
            },
          }),
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item {...tailFormItemLayout} name="submit">
        <Row>
          <Col flex="auto">
            <Button type="primary" htmlType="submit">
              Создать аккаунт
            </Button>
          </Col>
          <Col>
            <Button onClick={goToLogin}>Войти</Button>
          </Col>
        </Row>
      </Form.Item>
    </Form>
  );
}
