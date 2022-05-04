import { ChangeEvent, useCallback, useEffect, useMemo, useState } from 'react';
import { UserInfoForUpdateDTO, UserViewDTO } from '@src/generated/backend';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { doWithLocalSpinner } from '@src/redux/reducers/spinner';

export interface UseAdminAboutMeForm {
  currentUserInfo: UserInfoForUpdateDTO;
  setEmail: (event: ChangeEvent<HTMLInputElement>) => void;
  updateUserInfo: () => Promise<void>;
  isUpdated: boolean;
}

export const useAdminAboutMeForm = (): UseAdminAboutMeForm => {
  const [userInfo, setUserInfo] = useState<UserViewDTO>({
    email: '',
    username: '',
    address: '',
    privateKey: '',
  });
  const [currentUserInfo, setCurrentUserInfo] = useState<UserInfoForUpdateDTO>({
    email: '',
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
        email: info.email,
        address: info.address,
        privateKey: info.privateKey,
      });
    });
  }, [userControllerApi]);

  const isUpdated = useMemo(() => {
    return userInfo.email === currentUserInfo.email;
  }, [userInfo, currentUserInfo]);

  const setEmail = useCallback(
    (event: ChangeEvent<HTMLInputElement>) =>
      setCurrentUserInfo((prevState) => ({ ...prevState, email: event.target.value })),
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

  return {
    currentUserInfo,
    setEmail,
    updateUserInfo,
    isUpdated,
  };
};
