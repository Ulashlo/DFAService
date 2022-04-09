import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { ApiErrorInfo } from '@src/redux/reducers/apiError';

const selector = (state: RootStateType) => state.apiError.apiErrorInfo;

export const useApiErrorInfo = (): ApiErrorInfo | undefined => useSelector(selector);
