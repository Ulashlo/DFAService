import { Col, Row } from 'antd';
import React from 'react';
import { LoginForm, RegistrationForm } from '@src/components/auth';
import { useAuthForm } from '@src/forms/auth/hook';

export function AuthForm() {
  const { isLogin, goToLogin, goToRegister } = useAuthForm();
  return (
    <Row style={{ height: '100%' }} justify="space-around" align="middle">
      {isLogin && (
        <Col span={4}>
          <LoginForm goToRegister={goToRegister} />
        </Col>
      )}
      {!isLogin && (
        <Col span={7}>
          <RegistrationForm goToLogin={goToLogin} />
        </Col>
      )}
    </Row>
  );
}
