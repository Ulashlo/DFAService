import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { setAuthInfo } from '@src/redux/reducers/auth';
import { usePreviousPath } from '@src/components/auth/hook/usePreviousPath.hook';
import { doWithSpinner } from '@src/redux/reducers/spinner';

export interface UseLoginForm {
  authenticate: (username: string, password: string) => void;
}

export const useLoginForm = (): UseLoginForm => {
  const dispatch = useAppDispatch();
  const { goToPreviousPath } = usePreviousPath();
  const { authControllerApi } = useHttpClient();

  const authenticate = useCallback(
    async (nickname: string, password: string) => {
      try {
        const authInfo = await dispatch(doWithSpinner(() => authControllerApi.signIn({ nickname, password })));
        // const authInfo = await authControllerApi.signIn({ nickname, password });

        dispatch(setAuthInfo(authInfo));
        goToPreviousPath();
      } catch (error: any) {
        console.log(error);
      }
    },
    [dispatch, goToPreviousPath, authControllerApi],
  );

  return {
    authenticate,
  };
};
