import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { useInterval } from '@src/hooks/useInterval.hook';
import { INTERVAL } from '@src/utils/constraints';
import { setRequests } from '@src/redux/reducers/requests';

export const useAutoUpdateRequests = (delay?: number) => {
  const dispatch = useAppDispatch();
  const authInfo = useAuthInfo();

  const { exchangerControllerApi } = useHttpClient();
  const updateRequests = useCallback(async () => {
    const requests = await exchangerControllerApi.getAllExchanges();
    dispatch(setRequests(requests));
  }, [dispatch, exchangerControllerApi]);

  useInterval(authInfo ? updateRequests : () => {}, INTERVAL, delay);
};
