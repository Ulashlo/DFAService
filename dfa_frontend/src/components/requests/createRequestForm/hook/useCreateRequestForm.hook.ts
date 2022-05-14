import { useCallback, useMemo } from 'react';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { useAutoUpdateBalances } from '@src/hooks/useAutoUpdateBalances.hook';
import { DELAY } from '@src/utils/constraints';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import { DFABalanceDTO, DFAViewDTO, ExchangeRequestDTOTypeEnum } from '@src/generated/backend';
import NotificationService from '@src/services/NotificationService';
import { useDfasInfo } from '@src/redux/hooks/dfas';
import { useBalancesInfo } from '@src/redux/hooks/balances';
import { Moment } from 'moment';

export interface UseCreateRequestForm {
  dfaToBuy: DFAViewDTO[];
  dfaToSell: DFAViewDTO[];
  balances: DFABalanceDTO[];
  creteRequest: (info: ExchangeRequestFormInfo) => void;
}

export interface ExchangeRequestFormInfo {
  type: ExchangeRequestDTOTypeEnum;
  dfaToGet: string;
  amountToGet: number;
  dfaToGive: string;
  amountToGive: number;
  endTime: Moment;
}

export const useCreateRequestForm = (): UseCreateRequestForm => {
  useAutoUpdateDfas();
  useAutoUpdateBalances(DELAY);
  const { exchangerControllerApi } = useHttpClient();
  const creteRequest = useCallback(
    (info: ExchangeRequestFormInfo) => {
      exchangerControllerApi
        .addExchange({
          exchangeRequestDTO: {
            ...info,
            endTime: info.endTime.unix(),
          },
        })
        .then(() => NotificationService.reportSuccess(`Запрос успешно создан!`));
    },
    [exchangerControllerApi],
  );
  const dfas = useDfasInfo();
  const balances = useBalancesInfo();
  const dfaToBuy = useMemo(() => dfas, [dfas]);
  const dfaToSell = useMemo(
    () =>
      dfas.filter((dfa) => {
        const balance = balances.find((b) => b.address === dfa.address)?.balance ?? 0;
        return balance > 0;
      }),
    [dfas, balances],
  );

  return {
    dfaToBuy,
    dfaToSell,
    balances,
    creteRequest,
  };
};
