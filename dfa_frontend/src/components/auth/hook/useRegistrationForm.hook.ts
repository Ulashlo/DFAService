import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { setAuthInfo } from '@src/redux/reducers/auth';
import { UserInfoForCreateDTO } from '@src/generated/backend';
import { usePreviousPath } from '@src/components/auth/hook/usePreviousPath.hook';
import { doWithSpinner } from '@src/redux/reducers/spinner';

export interface UseRegistrationForm {
  registration: (userInfoForCreate: UserInfoForCreateDTO) => void;
}

export const useRegistrationForm = (): UseRegistrationForm => {
  const dispatch = useAppDispatch();
  const { goToPreviousPath } = usePreviousPath();
  const { authControllerApi } = useHttpClient();

  const registration = useCallback(
    async (userInfoForCreate: UserInfoForCreateDTO) => {
      try {
        const authInfo = await dispatch(
          doWithSpinner(() => authControllerApi.signUp({ userInfoForCreateDTO: userInfoForCreate })),
        );
        dispatch(setAuthInfo(authInfo));
        goToPreviousPath();
      } catch (error: any) {
        console.log(error);
      }
    },
    [authControllerApi, dispatch, goToPreviousPath],
  );

  return {
    registration,
  };
};
