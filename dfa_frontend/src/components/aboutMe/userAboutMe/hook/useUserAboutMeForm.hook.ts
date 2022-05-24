import { ChangeEvent, useCallback, useEffect, useMemo, useState } from 'react';
import { UserInfoForUpdateDTO, UserViewDTO } from '@src/generated/backend';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { doWithLocalSpinner } from '@src/redux/reducers/spinner';
import NotificationService from '@src/services/NotificationService';
import { isAddress } from 'web3-utils';

export interface UseUserAboutMeForm {
  currentUserInfo: UserInfoForUpdateDTO;
  setEmail: (event: ChangeEvent<HTMLInputElement>) => void;
  setAddress: (event: ChangeEvent<HTMLInputElement>) => void;
  setPrivateKey: (event: ChangeEvent<HTMLInputElement>) => void;
  updateUserInfo: () => Promise<void>;
  verify: () => Promise<void>;
  isNotUpdated: boolean;
  isCredentialsExist: boolean;
  isAddressValid: boolean;
}

export const useUserAboutMeForm = (): UseUserAboutMeForm => {
  const [userInfo, setUserInfo] = useState<UserViewDTO>({
    username: '',
    email: '',
    address: '',
    privateKey: '',
  });
  const [currentUserInfo, setCurrentUserInfo] = useState<UserInfoForUpdateDTO>({
    email: '',
    address: '',
    privateKey: '',
  });
  const { userControllerApi, requestControllerApi } = useHttpClient();

  useEffect(() => {
    doWithLocalSpinner(() => userControllerApi.getUserInfo(), {
      showSpinner: () => {},
      hideSpinner: () => {},
    }).then((info) => {
      setUserInfo(info);
      setCurrentUserInfo({
        email: info.email,
        address: info.address,
        privateKey: info.privateKey,
      });
    });
  }, [userControllerApi]);

  const isNotUpdated = useMemo(() => {
    return (
      userInfo.address === currentUserInfo.address &&
      userInfo.privateKey === currentUserInfo.privateKey &&
      userInfo.email === currentUserInfo.email
    );
  }, [userInfo, currentUserInfo]);

  const isAddressEmpty = useMemo(
    () => currentUserInfo.address === undefined || currentUserInfo.address.length === 0,
    [currentUserInfo.address],
  );

  const isPrivateKeyEmpty = useMemo(
    () => currentUserInfo.privateKey === undefined || currentUserInfo.privateKey.length === 0,
    [currentUserInfo.privateKey],
  );

  const isCredentialsExist = useMemo(() => {
    if (isAddressEmpty && isPrivateKeyEmpty) {
      return true;
    }
    return !isAddressEmpty && !isPrivateKeyEmpty;
  }, [isAddressEmpty, isPrivateKeyEmpty]);

  const isAddressValid = useMemo(() => {
    if (isAddressEmpty) {
      return true;
    }
    return isAddress(currentUserInfo.address ?? '');
  }, [currentUserInfo.address, isAddressEmpty]);

  const setEmail = useCallback(
    (event: ChangeEvent<HTMLInputElement>) =>
      setCurrentUserInfo((prevState) => ({ ...prevState, email: event.target.value })),
    [setCurrentUserInfo],
  );

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

  const updateUserInfo = useCallback(async () => {
    await userControllerApi.updateUserInfo({
      userInfoForUpdateDTO: currentUserInfo,
    });
    setUserInfo((prevState) => ({
      email: currentUserInfo.email ?? '',
      username: prevState.username,
      address: currentUserInfo.address ?? '',
      privateKey: currentUserInfo.privateKey ?? '',
    }));
  }, [userControllerApi, setUserInfo, currentUserInfo]);

  const verify = useCallback(async () => {
    await requestControllerApi.addIssuerRequest();
    NotificationService.reportSuccess('Заявка на верификацию отправлена');
  }, [requestControllerApi]);

  return {
    currentUserInfo,
    setEmail,
    setAddress,
    setPrivateKey,
    updateUserInfo,
    verify,
    isNotUpdated,
    isCredentialsExist,
    isAddressValid,
  };
};
