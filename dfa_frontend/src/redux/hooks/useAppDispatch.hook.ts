import { useDispatch } from 'react-redux';
import { AppDispatch } from '@src/index';

export const useAppDispatch = () => useDispatch<AppDispatch>();
