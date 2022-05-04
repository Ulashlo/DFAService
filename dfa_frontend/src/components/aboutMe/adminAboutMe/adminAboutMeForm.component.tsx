import React from 'react';
import { Button, Col, Input, Row, Typography } from 'antd';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { useAdminAboutMeForm } from '@src/components/aboutMe/adminAboutMe/hook';

export function AdminAboutMeForm() {
  const authInfo = useAuthInfo();
  const { isUpdated, updateUserInfo } = useAdminAboutMeForm();

  return (
    <Row style={{ height: '80%' }} justify="space-around" align="middle">
      <Col span={9}>
        <Row justify="center">
          <Typography.Title level={3}>Личные данные</Typography.Title>
        </Row>
        <Row>
          <Col span={8}>
            <Row justify="end" style={{ paddingTop: '20px' }}>
              <Typography.Title level={5}>Имя:</Typography.Title>
            </Row>
            <Row justify="end" style={{ paddingTop: '20px' }}>
              <Typography.Title level={5}>Email:</Typography.Title>
            </Row>
          </Col>
          <Col span={15} offset={1}>
            <Row justify="start" style={{ paddingTop: '20px' }}>
              <Typography.Title level={5}>{authInfo?.username}</Typography.Title>
            </Row>
            <Row justify="start" style={{ paddingTop: '20px' }}>
              <Input />
            </Row>
            <Row justify="start" style={{ paddingTop: '20px' }}>
              <Button type="primary" htmlType="submit" disabled={isUpdated} onClick={updateUserInfo}>
                Сохранить
              </Button>
            </Row>
          </Col>
        </Row>
      </Col>
    </Row>
  );
}
