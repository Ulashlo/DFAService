import { useCallback, useMemo, useState } from 'react';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { useAutoUpdateBalances } from '@src/hooks/useAutoUpdateBalances.hook';
import { DELAY } from '@src/utils/constraints';
import { useDfasIsLoad, useDfaViews } from '@src/redux/hooks/dfas';
import { useBalancesInfo, useBalancesIsLoad } from '@src/redux/hooks/balances';
import { Balance } from '@src/components/dfa/dfaTable';
import NotificationService from '@src/services/NotificationService';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';

export interface UseBalanceForm {
  isLoading: boolean;
  dataSource: Balance[];
  amount?: number;
  setAmount: (val: number) => void;
  addBalance: () => void;
  isAddBalanceEnabled: boolean;
}

export const useBalanceForm = (): UseBalanceForm => {
  useAutoUpdateDfas();
  useAutoUpdateBalances(DELAY);
  const [amount, setAmount] = useState<number>();
  const { dfaControllerApi } = useHttpClient();
  const dfas = useDfaViews();
  const isDfasLoading = !useDfasIsLoad();
  const balances = useBalancesInfo();
  const isBalancesLoading = !useBalancesIsLoad();
  const isLoading = useMemo(() => isDfasLoading || isBalancesLoading, [isDfasLoading, isBalancesLoading]);
  const isAddBalanceEnabled = Boolean(amount ? amount > 0 : false);

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

  const addBalance = useCallback(async () => {
    await dfaControllerApi.replenishBalance({ amount: amount ?? 0 });
    NotificationService.reportSuccess('Заявка на пополнение баланса отправлена!');
  }, [dfaControllerApi, amount]);

  return {
    isLoading,
    dataSource,
    amount,
    setAmount,
    addBalance,
    isAddBalanceEnabled,
  };
};
