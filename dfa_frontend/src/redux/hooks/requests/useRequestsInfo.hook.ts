import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { RequestsInfo } from '@src/redux/reducers/requests';

const selector = (state: RootStateType) => state.requests.requestsInfo;

export const useRequestsInfo = (): RequestsInfo => useSelector(selector);
