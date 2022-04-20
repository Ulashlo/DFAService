import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { DFAViewDto } from '@src/generated/backend';

const selector = (state: RootStateType) => state.data.dfasInfo.dfas;

export const useDfasInfo = (): DFAViewDto[] => useSelector(selector);
