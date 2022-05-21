import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { useInterval } from '@src/hooks/useInterval.hook';
import { INTERVAL, TRADER } from '@src/utils/constraints';
import { setCompletedExchanges } from '@src/redux/reducers/completedExchanges';

export const useAutoUpdateCompletedExchanges = (delay?: number) => {
  const dispatch = useAppDispatch();
  const authInfo = useAuthInfo();

  const { exchangerControllerApi } = useHttpClient();
  const updateCompletedExchanges = useCallback(async () => {
    const exchanges = await exchangerControllerApi.getAllCompletedExchanges();
    dispatch(setCompletedExchanges(exchanges));
  }, [dispatch, exchangerControllerApi]);

  useInterval(authInfo && authInfo.roles.includes(TRADER) ? updateCompletedExchanges : () => {}, INTERVAL, delay);
};
