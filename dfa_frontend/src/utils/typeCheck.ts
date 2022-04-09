import { AuthInfo } from '@src/redux/reducers/auth/auth.reducer';
import { ApiErrorInfo } from '@src/redux/reducers/apiError';
import { ApiError } from '@src/generated/backend';

export const isAuthInfo = (o: any): o is AuthInfo => {
  return isObject(o) && typeof o.token === 'string' && typeof o.username === 'string' && isArrayOfString(o.roles);
};

export const isApiErrorValues = (o: any): o is ApiError => {
  return isObject(o) && dateIsValid(o.errorDateTime) && typeof o.status === 'number' && typeof o.message === 'string';
};

export const isApiErrorInfo = (o: any): o is ApiErrorInfo => {
  return isObject(o) && typeof o.shown === 'boolean' && isApiErrorValues(o.info);
};

export function isArrayOfString(arr: any) {
  return isArrayOf(arr, (item: any) => typeof item === 'string');
}

export function isArrayOf(arr: any, predicate: (item: any) => boolean) {
  return Array.isArray(arr) && arr.every(predicate);
}

export function isEnum(possibleEnumValue: any, enumType: object) {
  return Object.values(enumType).includes(possibleEnumValue);
}

export function isObject(o: any) {
  return o !== null && typeof o === 'object' && !Array.isArray(o);
}

export function dateIsValid(date: Date) {
  return !Number.isNaN(new Date(date).getTime());
}
