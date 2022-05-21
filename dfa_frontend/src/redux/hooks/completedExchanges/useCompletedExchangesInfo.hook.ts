import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { CompletedExchangesInfo } from '@src/redux/reducers/completedExchanges';

const selector = (state: RootStateType) => state.completedExchanges.completedExchangesInfo;

export const useCompletedExchangesInfo = (): CompletedExchangesInfo => useSelector(selector);
