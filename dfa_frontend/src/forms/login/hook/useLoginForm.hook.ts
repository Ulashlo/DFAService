import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { setAuthInfo } from '@src/redux/reducers/auth';
import { useHistory } from 'react-router-dom';

export interface UseLoginForm {
  authenticate: (username: string, password: string) => void;
}
// TODO добавить spinner
export const useLoginForm = () => {
  const dispatch = useAppDispatch();
  const history = useHistory<{ from: string | string[] }>();
  const { authControllerApi } = useHttpClient();

  const authenticate = useCallback(
    async (nickname: string, password: string) => {
      const authInfo = await authControllerApi.signIn({ nickname, password });
      dispatch(setAuthInfo(authInfo));
      const from = history.location.state?.from ?? '';
      if (from && Array.isArray(from)) {
        history.push(from.join('/'));
      } else if (from) {
        history.push(from);
      } else {
        history.push('/');
      }
    },
    [dispatch, history, authControllerApi],
  );
  return {
    authenticate,
  };
};
