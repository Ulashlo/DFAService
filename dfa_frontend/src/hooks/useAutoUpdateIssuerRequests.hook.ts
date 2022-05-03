import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { useInterval } from '@src/hooks/useInterval.hook';
import { INTERVAL } from '@src/utils/constraints';
import { setIssuerRequests } from '@src/redux/reducers/issuerRequests';

export const useAutoUpdateIssuerRequests = (delay?: number) => {
  const dispatch = useAppDispatch();
  const authInfo = useAuthInfo();

  const { requestControllerApi } = useHttpClient();
  const updateRequests = useCallback(async () => {
    const requests = await requestControllerApi.getCreatedIssuerRequest();
    dispatch(setIssuerRequests(requests));
  }, [dispatch, requestControllerApi]);

  useInterval(authInfo ? updateRequests : () => {}, INTERVAL, delay);
};
