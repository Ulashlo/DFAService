import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { DFABalanceDTO } from '@src/generated/backend';

const selector = (state: RootStateType) => state.balances.balancesInfo.balances;

export const useBalancesInfo = (): DFABalanceDTO[] => useSelector(selector);
