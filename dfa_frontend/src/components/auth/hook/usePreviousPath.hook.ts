import { useHistory } from 'react-router-dom';
import { useCallback } from 'react';

export interface UsePreviousPath {
  goToPreviousPath: () => void;
}

export const usePreviousPath = (): UsePreviousPath => {
  const history = useHistory<{ from: string | string[] }>();

  const goToPreviousPath = useCallback(() => {
    const from = history.location.state?.from ?? '';
    if (from && Array.isArray(from)) {
      history.push(from.join('/'));
    } else if (from) {
      history.push(from);
    } else {
      history.push('/');
    }
  }, [history]);

  return {
    goToPreviousPath,
  };
};
