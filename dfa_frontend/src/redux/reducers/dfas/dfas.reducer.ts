/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { DFAViewDTO } from '@src/generated/backend';

export interface DfasInfo {
  dfas: DFAViewDTO[];
  idDfasLoad: boolean;
}

export interface DfasStateType {
  dfasInfo: DfasInfo;
}

const initialState: DfasStateType = {
  dfasInfo: {
    dfas: [],
    idDfasLoad: false,
  },
};

const dataSlice = createSlice({
  name: 'dfas',
  initialState,
  reducers: {
    setDfas: (state, action: PayloadAction<DFAViewDTO[]>) => {
      state.dfasInfo.dfas = action.payload;
      state.dfasInfo.idDfasLoad = true;
    },
  },
});

export const dfasReducer = dataSlice.reducer;
export const { setDfas } = dataSlice.actions;
