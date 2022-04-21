import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';

const selector = (state: RootStateType) => state.balances.balancesInfo.isBalanceLoad;

export const useBalancesIsLoad = (): boolean => useSelector(selector);
