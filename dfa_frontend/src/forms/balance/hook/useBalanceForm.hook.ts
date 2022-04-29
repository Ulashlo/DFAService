import { useMemo } from 'react';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { useAutoUpdateBalances } from '@src/hooks/useAutoUpdateBalances.hook';
import { DELAY } from '@src/utils/constraints';
import { useDfasIsLoad, useDfaViews } from '@src/redux/hooks/dfas';
import { useBalancesInfo, useBalancesIsLoad } from '@src/redux/hooks/balances';
import { Balance } from '@src/components/dfa/dfaTable';

export interface UseBalanceForm {
  isLoading: boolean;
  dataSource: Balance[];
}

export const useBalanceForm = (): UseBalanceForm => {
  useAutoUpdateDfas();
  useAutoUpdateBalances(DELAY);

  const dfas = useDfaViews();
  const isDfasLoading = !useDfasIsLoad();
  const balances = useBalancesInfo();
  const isBalancesLoading = !useBalancesIsLoad();
  const isLoading = useMemo(() => isDfasLoading || isBalancesLoading, [isDfasLoading, isBalancesLoading]);
  const dataSource = useMemo(() => {
    return balances
      .filter((balance) => balance.address.length > 0)
      .map((balance): Balance => {
        if (dfas) {
          const dfa = dfas[balance.address];
          return {
            ...dfa,
            ...balance,
          };
        }
        return {
          address: '',
          owner: '',
          name: '',
          symbol: '',
          totalSupply: 0,
          balance: 0,
        };
      })
      .sort((f: Balance, s: Balance) => f.name.localeCompare(s.name));
  }, [balances, dfas]);

  return {
    isLoading,
    dataSource,
  };
};
