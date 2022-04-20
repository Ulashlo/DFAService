import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';

const selector = (state: RootStateType) => state.data.dataInfo.idDfasLoad;

export const useDfasIsLoad = (): boolean => useSelector(selector);
