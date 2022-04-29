import { ChangeEvent, useCallback, useEffect, useMemo, useState } from 'react';
import { UserInfoForUpdateDTO, UserViewDTO } from '@src/generated/backend';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { doWithLocalSpinner } from '@src/redux/reducers/spinner';

export interface UseAboutMeForm {
  currentUserInfo: UserInfoForUpdateDTO;
  setAddress: (event: ChangeEvent<HTMLInputElement>) => void;
  setPrivateKey: (event: ChangeEvent<HTMLInputElement>) => void;
  updateUserInfo: () => Promise<void>;
  isUpdated: boolean;
}

export const useAboutMeForm = (): UseAboutMeForm => {
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

  const updateUserInfo = useCallback(async () => {
    await userControllerApi.updateUserInfo({
      userInfoForUpdateDTO: currentUserInfo,
    });
    setUserInfo((prevState) => ({
      username: prevState.username,
      address: currentUserInfo.address ?? '',
      privateKey: currentUserInfo.privateKey ?? '',
    }));
  }, [userControllerApi, setUserInfo, currentUserInfo]);

  return {
    currentUserInfo,
    setAddress,
    setPrivateKey,
    updateUserInfo,
    isUpdated,
  };
};
