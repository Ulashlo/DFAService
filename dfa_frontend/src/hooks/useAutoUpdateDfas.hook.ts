import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { setDfas } from '@src/redux/reducers/dfas';
import { useInterval } from '@src/hooks/useInterval.hook';
import { INTERVAL } from '@src/utils/constraints';

export const useAutoUpdateDfas = () => {
  const dispatch = useAppDispatch();
  const authInfo = useAuthInfo();

  const { dfaControllerApi } = useHttpClient();
  const updateDfas = useCallback(async () => {
    const dfas = await dfaControllerApi.getAllDfa();
    dispatch(setDfas(dfas));
  }, [dispatch, dfaControllerApi]);

  useInterval(authInfo ? updateDfas : () => {}, INTERVAL);
};
