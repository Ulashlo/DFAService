import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { useCallback } from 'react';
import { DFAViews, setDfas, setDfaViews } from '@src/redux/reducers/dfas';
import { useInterval } from '@src/hooks/useInterval.hook';
import { INTERVAL } from '@src/utils/constraints';

export const useAutoUpdateDfas = (delay?: number) => {
  const dispatch = useAppDispatch();
  const authInfo = useAuthInfo();

  const { dfaControllerApi } = useHttpClient();
  const updateDfas = useCallback(async () => {
    const dfas = await dfaControllerApi.getAllDfa();
    const dfaVies: DFAViews = {};
    dfas.forEach((dfa) => {
      dfaVies[dfa.address] = {
        owner: dfa.owner,
        name: dfa.name,
        symbol: dfa.symbol,
        totalSupply: dfa.totalSupply,
      };
    });
    dispatch(setDfas(dfas));
    dispatch(setDfaViews(dfaVies));
  }, [dispatch, dfaControllerApi]);

  useInterval(authInfo ? updateDfas : () => {}, INTERVAL, delay);
};
