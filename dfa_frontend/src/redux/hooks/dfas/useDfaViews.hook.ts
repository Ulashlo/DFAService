import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { DFAViews } from '@src/redux/reducers/dfas';

const selector = (state: RootStateType) => state.dfaViews.dfaViesInfo?.dfaViews;

export const useDfaViews = (): DFAViews | undefined => useSelector(selector);
