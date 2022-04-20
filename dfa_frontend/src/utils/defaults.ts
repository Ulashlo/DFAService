import { DataInfo } from '@src/redux/reducers/data';

export const getDefaultDataInfo = (): DataInfo => {
  return {
    dfas: [],
    idDfasLoad: false,
  };
};
