import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { useInterval } from '@src/hooks/useInterval.hook';
import { INTERVAL, TRADER } from '@src/utils/constraints';
import { setBalances } from '@src/redux/reducers/balances';

export const useAutoUpdateBalances = (delay?: number) => {
  const dispatch = useAppDispatch();
  const authInfo = useAuthInfo();

  const { dfaControllerApi } = useHttpClient();
  const updateBalances = useCallback(async () => {
    if (authInfo && authInfo.address && authInfo.address.length > 0) {
      const balances = await dfaControllerApi.getBalances();
      dispatch(setBalances(balances));
    } else {
      dispatch(setBalances([]));
    }
  }, [dispatch, dfaControllerApi, authInfo]);

  useInterval(authInfo && authInfo.roles.includes(TRADER) ? updateBalances : () => {}, INTERVAL, delay);
};
