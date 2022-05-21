/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { CompletedExchangeDTO } from '@src/generated/backend';

export interface CompletedExchangesInfo {
  completedExchanges: CompletedExchangeDTO[];
  isCompletedExchangesLoad: boolean;
}

export interface CompletedExchangesStateType {
  completedExchangesInfo: CompletedExchangesInfo;
}

const initialState: CompletedExchangesStateType = {
  completedExchangesInfo: {
    completedExchanges: [],
    isCompletedExchangesLoad: false,
  },
};

const dataSlice = createSlice({
  name: 'completedExchanges',
  initialState,
  reducers: {
    setCompletedExchanges: (state, action: PayloadAction<CompletedExchangeDTO[]>) => {
      state.completedExchangesInfo.completedExchanges = action.payload;
      state.completedExchangesInfo.isCompletedExchangesLoad = true;
    },
    clearCompletedExchanges: (state) => {
      state.completedExchangesInfo.completedExchanges = [];
      state.completedExchangesInfo.isCompletedExchangesLoad = false;
    },
  },
});

export const completedExchangesReducer = dataSlice.reducer;
export const { setCompletedExchanges, clearCompletedExchanges } = dataSlice.actions;
