import { RootStateType } from '@src/redux/storeFactory';
import { AuthInfo } from '@src/redux/reducers/auth';
import { useSelector } from 'react-redux';

const selector = (state: RootStateType) => state.auth.authInfo;

export const useAuthInfo = (): AuthInfo | undefined => useSelector(selector);
