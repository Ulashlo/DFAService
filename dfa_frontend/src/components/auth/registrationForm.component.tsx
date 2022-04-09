import { useRegistrationForm } from '@src/components/auth/hook';
import { Button, Col, Form, Input, Row } from 'antd';
import React from 'react';
import { UserInfoForCreateDTO } from '@src/generated/backend';

export interface RegisterFormProps {
  goToLogin: () => void;
}

function getRequiredRule(message: string): { required: boolean; message: string } {
  return { required: true, message };
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

  return (
    <Form
      {...formItemLayout}
      style={{ padding: '10px' }}
      size="large"
      onFinish={(userInfoForCreate: UserInfoForCreateDTO) => registration(userInfoForCreate)}
    >
      <Form.Item name="username" label="Имя пользователя" rules={[getRequiredRule('Nickname не может быть пустым!')]}>
        <Input />
      </Form.Item>
      <Form.Item name="password" label="Пароль" rules={[getRequiredRule('Пароль не может быть пустым!')]}>
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
              return Promise.reject(new Error('The two passwords that you entered do not match!'));
            },
          }),
        ]}
      >
        <Input.Password />
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
