import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { IssuerRequestsInfo } from '@src/redux/reducers/issuerRequests';

const selector = (state: RootStateType) => state.issuerRequests.requestsInfo;

export const useIssuerRequestsInfo = (): IssuerRequestsInfo => useSelector(selector);
