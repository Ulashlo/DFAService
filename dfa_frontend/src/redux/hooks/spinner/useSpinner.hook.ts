import { RootStateType } from '@src/redux/storeFactory';
import { useSelector } from 'react-redux';
import { SpinnerState } from '@src/redux/reducers/spinner';

const selector = (state: RootStateType) => state.spinner;

export const useSpinner = (): SpinnerState => useSelector(selector);
