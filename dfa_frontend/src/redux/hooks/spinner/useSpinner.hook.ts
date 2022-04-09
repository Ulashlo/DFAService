import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { hideSpinner, showSpinner } from '@src/redux/reducers/spinner';

export async function useSpinner<T>(asyncFunc: () => Promise<T>, info?: string): Promise<T> {
  const dispatch = useAppDispatch();
  dispatch(showSpinner(info));
  try {
    return await asyncFunc();
  } finally {
    dispatch(hideSpinner());
  }
}
