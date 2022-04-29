import React from 'react';
import { Button, Col, Input, Row, Typography } from 'antd';
import { useAboutMeForm } from '@src/forms/aboutMe/hook';

export function AboutMeForm() {
  const { currentUserInfo, setAddress, setPrivateKey, updateUserInfo, isUpdated } = useAboutMeForm();
  return (
    <Row style={{ height: '80%' }} justify="space-around" align="middle">
      <Col span={9}>
        <Row justify="center">
          <Typography.Title level={3}>Данные аккаунта Ethereum</Typography.Title>
        </Row>
        <Row>
          <Col span={8}>
            <Row justify="end" style={{ paddingTop: '20px' }}>
              <Typography.Title level={5}>Адресс Ethereum:</Typography.Title>
            </Row>
            <Row justify="end" style={{ paddingTop: '20px' }}>
              <Typography.Title level={5}>Приватный ключ:</Typography.Title>
            </Row>
          </Col>
          <Col span={15} offset={1}>
            <Row justify="start" style={{ paddingTop: '20px' }}>
              <Input value={currentUserInfo.address} onChange={setAddress} />
            </Row>
            <Row justify="start" style={{ paddingTop: '20px' }}>
              <Input value={currentUserInfo.privateKey} onChange={setPrivateKey} />
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
