import {
  ApiError,
  AuthControllerApi,
  Configuration,
  DfaControllerApi,
  ExchangerControllerApi,
  FetchParams,
  RequestContext,
  ResponseContext,
} from '@src/generated/backend';
import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useAuthInfo } from '@src/redux/hooks/auth/useAuthInfo.hook';
import { Dispatch } from 'redux';
import { NOT_ACCEPTABLE, UNAUTHORIZED } from '@src/utils/httpCodes';
import { clearAuthInfo } from '@src/redux/reducers/auth';
import { useMemo } from 'react';
import { setApiErrorInfo } from '@src/redux/reducers/apiError';

export interface HttpClient {
  authControllerApi: AuthControllerApi;
  dfaControllerApi: DfaControllerApi;
  exchangerControllerApi: ExchangerControllerApi;
}

export const useHttpClient = (): Readonly<HttpClient> => {
  const dispatch = useAppDispatch();
  const authInfo = useAuthInfo();
  return useMemo(
    () => createHttpClient(dispatch, createApiConfiguration(dispatch, authInfo?.token)),
    [dispatch, authInfo],
  );
};

function createApiConfiguration(dispatch: Dispatch<any>, token?: string): Configuration {
  return new Configuration({
    basePath: window.document.location.origin + process.env.PUBLIC_URL,
    middleware: [
      {
        async pre(context: RequestContext): Promise<FetchParams | void> {
          context.init.headers = {
            'Content-Type': 'application/json;charset=UTF-8',
            Authorization: token ? `Bearer ${token}` : '',
          };
          return context;
        },
        post(context: ResponseContext): Promise<Response | void> {
          if (context.response.ok) {
            return Promise.resolve(context.response);
          }
          if ([UNAUTHORIZED, NOT_ACCEPTABLE].includes(context.response.status)) {
            dispatch(clearAuthInfo());
          }
          context.response
            .json()
            .then<ApiError>((error: ApiError) => {
              if (error) {
                return error;
              }
              return {
                errorDateTime: new Date(),
                status: 500,
                message: 'Невозможно распарсить ошибку с бэкенд. Обратитесь к администратору',
              };
            })
            .then((error: ApiError) => dispatch(setApiErrorInfo({ info: error, shown: true })));
          return Promise.reject();
        },
      },
    ],
  });
}

function createHttpClient(dispatch: Dispatch, configuration?: Configuration): HttpClient {
  return {
    authControllerApi: new AuthControllerApi(configuration),
    dfaControllerApi: new DfaControllerApi(configuration),
    exchangerControllerApi: new ExchangerControllerApi(configuration),
  };
}
