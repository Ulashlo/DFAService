/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { DFABalanceDTO } from '@src/generated/backend';

export interface BalancesInfo {
  balances: DFABalanceDTO[];
  isBalanceLoad: boolean;
}

export interface BalancesStateType {
  balancesInfo: BalancesInfo;
}

const initialState: BalancesStateType = {
  balancesInfo: {
    balances: [],
    isBalanceLoad: false,
  },
};

const dataSlice = createSlice({
  name: 'balances',
  initialState,
  reducers: {
    setBalances: (state, action: PayloadAction<DFABalanceDTO[]>) => {
      state.balancesInfo.balances = action.payload;
      state.balancesInfo.isBalanceLoad = true;
    },
  },
});

export const balancesReducer = dataSlice.reducer;
export const { setBalances } = dataSlice.actions;
