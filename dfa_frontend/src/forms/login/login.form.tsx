import { useLoginForm } from '@src/forms/login/hook';
import { Button, Col, Form, Input, Row } from 'antd';
import React from 'react';

export function LoginForm() {
  const { authenticate } = useLoginForm();

  return (
    <Row style={{ height: '100%' }}>
      <Col>
        <Form
          size="large"
          onFinish={({ nickname, password }: { nickname: string; password: string }) =>
            authenticate(nickname, password)
          }
        >
          <Form.Item name="nickname">
            <Input placeholder="Логин" />
          </Form.Item>
          <Form.Item name="password">
            <Input placeholder="Пароль" />
          </Form.Item>
          <Form.Item name="nickname">
            <Button type="primary" htmlType="submit">
              Войти
            </Button>
          </Form.Item>
        </Form>
      </Col>
    </Row>
  );
}
