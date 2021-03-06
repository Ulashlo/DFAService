import React from 'react';
import { Alert, Button, Col, Input, Row, Typography } from 'antd';
import { useUserAboutMeForm } from '@src/components/aboutMe/userAboutMe/hook';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { ISSUER } from '@src/utils/constraints';

export function UserAboutMeForm() {
  const {
    currentUserInfo,
    setEmail,
    setAddress,
    setPrivateKey,
    updateUserInfo,
    verify,
    isNotUpdated,
    isCredentialsExist,
    isAddressValid,
  } = useUserAboutMeForm();
  const authInfo = useAuthInfo();

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
            <Row justify="end" style={{ paddingTop: '20px' }}>
              <Typography.Title level={5}>Верификация:</Typography.Title>
            </Row>
          </Col>
          <Col span={15} offset={1}>
            <Row justify="start" style={{ paddingTop: '20px' }}>
              <Typography.Title level={5}>{authInfo?.username}</Typography.Title>
            </Row>
            <Row justify="start" style={{ paddingTop: '20px' }}>
              <Input value={currentUserInfo.email} onChange={setEmail} />
            </Row>
            <Row justify="start" style={{ paddingTop: '20px' }}>
              {authInfo?.roles.includes(ISSUER) ? (
                <Typography.Title type="success" level={5}>
                  Верифицирован
                </Typography.Title>
              ) : (
                <>
                  <Col span={12}>
                    <Typography.Title type="warning" level={5}>
                      Не верифицирован
                    </Typography.Title>
                  </Col>
                  <Col span={12}>
                    <Row justify="end">
                      <Button type="primary" htmlType="submit" onClick={verify}>
                        Верифицировать
                      </Button>
                    </Row>
                  </Col>
                </>
              )}
            </Row>
          </Col>
        </Row>
        <Row justify="center" style={{ paddingTop: '20px' }}>
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
            {authInfo?.roles.includes(ISSUER) ? (
              <>
                <Row justify="start" style={{ paddingTop: '20px' }}>
                  <Typography.Title level={5}>{currentUserInfo.address}</Typography.Title>
                </Row>
                <Row justify="start" style={{ paddingTop: '20px' }}>
                  <Typography.Title level={5}>{currentUserInfo.privateKey}</Typography.Title>
                </Row>
              </>
            ) : (
              <>
                <Row justify="start" style={{ paddingTop: '20px' }}>
                  <Input value={currentUserInfo.address} onChange={setAddress} />
                </Row>
                <Row justify="start" style={{ paddingTop: '20px' }}>
                  <Input value={currentUserInfo.privateKey} onChange={setPrivateKey} />
                </Row>
              </>
            )}
            {!isAddressValid ? (
              <Row justify="start" style={{ paddingTop: '20px' }}>
                <Alert type="error" message="Некорректный адрес!" />
              </Row>
            ) : (
              !isCredentialsExist && (
                <Row justify="start" style={{ paddingTop: '20px' }}>
                  <Alert type="error" message="Укажите и аддресс, и приватный ключ!" />
                </Row>
              )
            )}
            <Row justify="start" style={{ paddingTop: '20px' }}>
              <Button
                type="primary"
                htmlType="submit"
                disabled={isNotUpdated || !isCredentialsExist || !isAddressValid}
                onClick={updateUserInfo}
              >
                Сохранить
              </Button>
            </Row>
          </Col>
        </Row>
      </Col>
    </Row>
  );
}
