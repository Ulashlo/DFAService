import { useCallback, useState } from 'react';

export interface UseAuthForm {
  isLogin: boolean;
  goToLogin: () => void;
  goToRegister: () => void;
}

export const useAuthForm = (): UseAuthForm => {
  const [isLogin, setIsLogin] = useState(true);

  const goToLogin = useCallback(() => {
    setIsLogin(true);
  }, [setIsLogin]);

  const goToRegister = useCallback(() => {
    setIsLogin(false);
  }, [setIsLogin]);

  return {
    isLogin,
    goToLogin,
    goToRegister,
  };
};
