import { AuthInfo } from '@src/redux/reducers/auth/auth.reducer';
import { Try } from '@src/utils/Try';
import { isApiErrorInfo, isAuthInfo } from '@src/utils/typeCheck';
import { ApiErrorInfo } from '@src/redux/reducers/apiError';

export interface LocalStorageValue<ACCEPT_TYPE, RETURN_TYPE = ACCEPT_TYPE> {
  set: (value: ACCEPT_TYPE) => void;
  get: () => RETURN_TYPE;
}

export type PartialLocalStorageValue<ACCEPT_TYPE> = LocalStorageValue<ACCEPT_TYPE, ACCEPT_TYPE | undefined>;

export class LocalStorageService {
  public static authInfo(): LocalStorageValue<AuthInfo | undefined> {
    const key = 'authInfoKey';
    return {
      set: (authInfo?: AuthInfo) => {
        if (authInfo) {
          localStorage.setItem(key, JSON.stringify(authInfo));
        } else {
          localStorage.removeItem(key);
        }
      },
      get: () => {
        const parsed = Try.of(() => JSON.parse(localStorage.getItem(key) ?? '')).orElse(undefined);
        return isAuthInfo(parsed) ? parsed : undefined;
      },
    };
  }

  public static apiErrorInfo(): LocalStorageValue<ApiErrorInfo | undefined> {
    const key = 'apiErrorInfoKey';
    return {
      set: (apiErrorInfo?: ApiErrorInfo) => {
        if (apiErrorInfo) {
          localStorage.setItem(key, JSON.stringify(apiErrorInfo));
        } else {
          localStorage.removeItem(key);
        }
      },
      get: () => {
        const parsed = Try.of(() => JSON.parse(localStorage.getItem(key) ?? '')).orElse(undefined);
        return isApiErrorInfo(parsed) ? parsed : undefined;
      },
    };
  }
}
