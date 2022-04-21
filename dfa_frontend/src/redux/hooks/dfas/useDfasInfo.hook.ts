import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { DFAViewDTO } from '@src/generated/backend';

const selector = (state: RootStateType) => state.dfas.dfasInfo.dfas;

export const useDfasInfo = (): DFAViewDTO[] => useSelector(selector);
