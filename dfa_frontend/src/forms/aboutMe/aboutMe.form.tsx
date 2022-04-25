import React, { ChangeEvent, useCallback, useEffect, useMemo, useState } from 'react';
import { Button, Col, Input, Row, Typography } from 'antd';
import { UserInfoForUpdateDTO, UserViewDTO } from '@src/generated/backend';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { doWithLocalSpinner } from '@src/redux/reducers/spinner';

export function AboutMeForm() {
  const [userInfo, setUserInfo] = useState<UserViewDTO>({
    username: '',
    address: '',
    privateKey: '',
  });
  const [currentUserInfo, setCurrentUserInfo] = useState<UserInfoForUpdateDTO>({
    address: '',
    privateKey: '',
  });
  const { userControllerApi } = useHttpClient();

  useEffect(() => {
    doWithLocalSpinner(() => userControllerApi.getUserInfo(), {
      showSpinner: () => {},
      hideSpinner: () => {},
    }).then((info) => {
      setUserInfo(info);
      setCurrentUserInfo({
        address: info.address,
        privateKey: info.privateKey,
      });
    });
  }, [userControllerApi]);

  const isUpdated = useMemo(() => {
    return userInfo.address === currentUserInfo.address && userInfo.privateKey === currentUserInfo.privateKey;
  }, [userInfo, currentUserInfo]);

  const setAddress = useCallback(
    (event: ChangeEvent<HTMLInputElement>) =>
      setCurrentUserInfo((prevState) => ({ ...prevState, address: event.target.value })),
    [setCurrentUserInfo],
  );

  const setPrivateKey = useCallback(
    (event: ChangeEvent<HTMLInputElement>) =>
      setCurrentUserInfo((prevState) => ({ ...prevState, privateKey: event.target.value })),
    [setCurrentUserInfo],
  );

  const updateUserInfo = async () => {
    await userControllerApi.updateUserInfo({
      userInfoForUpdateDTO: currentUserInfo,
    });
    setUserInfo((prevState) => ({
      username: prevState.username,
      address: currentUserInfo.address ?? '',
      privateKey: currentUserInfo.privateKey ?? '',
    }));
    console.log('done');
  };

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
