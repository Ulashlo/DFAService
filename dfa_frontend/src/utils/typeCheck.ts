import { AuthInfo } from '@src/redux/reducers/auth/auth.reducer';

export const isAuthInfo = (o: any): o is AuthInfo => {
  return isObject(o) && typeof o.token === 'string' && typeof o.username === 'string' && isArrayOfString(o.roles);
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
