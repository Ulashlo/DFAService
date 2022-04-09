import { useLoginForm } from '@src/components/auth/hook';
import { Button, Col, Form, Input, Row } from 'antd';
import React from 'react';

export interface LoginFormProps {
  goToRegister: () => void;
}

function getRequiredRule(message: string): [{ required: boolean; message: string }] {
  return [{ required: true, message }];
}

export function LoginForm({ goToRegister }: LoginFormProps) {
  const { authenticate } = useLoginForm();

  return (
    <Form
      style={{ padding: '10px' }}
      size="large"
      onFinish={({ nickname, password }: { nickname: string; password: string }) => authenticate(nickname, password)}
    >
      <Form.Item name="nickname" rules={getRequiredRule('Логин не может быть пустым!')}>
        <Input placeholder="Логин" />
      </Form.Item>
      <Form.Item name="password" rules={getRequiredRule('Пароль не может быть пустым!')}>
        <Input placeholder="Пароль" />
      </Form.Item>
      <Form.Item name="submit">
        <Row>
          <Col flex="auto">
            <Button type="primary" htmlType="submit">
              Войти
            </Button>
          </Col>
          <Col>
            <Button onClick={goToRegister}>Зарегистрироваться</Button>
          </Col>
        </Row>
      </Form.Item>
    </Form>
  );
}
