import { useLoginForm } from '@src/components/auth/hook';
import { Button, Col, Form, Input, Row } from 'antd';
import React from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { useHistory } from 'react-router-dom';
import { pages } from '@src/CustomRouter';

export interface LoginFormProps {
  goToRegister: () => void;
}

function getRequiredRule(message: string): [{ required: boolean; message: string }] {
  return [{ required: true, message }];
}

export function LoginForm({ goToRegister }: LoginFormProps) {
  const { authenticate } = useLoginForm();
  const history = useHistory();

  return (
    <Form
      style={{ padding: '10px' }}
      size="large"
      onFinish={async ({ nickname, password }: { nickname: string; password: string }) => {
        await authenticate(nickname, password);
        history.push(pages.dfa.uri);
      }}
    >
      <Form.Item name="nickname" rules={getRequiredRule('Логин не может быть пустым!')}>
        <Input placeholder="Логин" prefix={<UserOutlined />} />
      </Form.Item>
      <Form.Item name="password" rules={getRequiredRule('Пароль не может быть пустым!')}>
        <Input.Password placeholder="Пароль" prefix={<LockOutlined />} />
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
