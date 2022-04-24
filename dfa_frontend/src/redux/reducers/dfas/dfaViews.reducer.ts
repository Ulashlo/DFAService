/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface DFAView {
  owner: string;
  name: string;
  symbol: string;
  totalSupply: number;
}

export type DFAViews = {
  [key: string]: DFAView;
};

export interface DFAViewsInfo {
  dfaViews: DFAViews;
}

export interface DFAViewsStateType {
  dfaViesInfo?: DFAViewsInfo;
}

const initialState: DFAViewsStateType = {
  dfaViesInfo: undefined,
};

const dataSlice = createSlice({
  name: 'dfaViews',
  initialState,
  reducers: {
    setDfaViews: (state, action: PayloadAction<DFAViews>) => {
      state.dfaViesInfo = { dfaViews: action.payload };
    },
  },
});

export const dfaViewsReducer = dataSlice.reducer;
export const { setDfaViews } = dataSlice.actions;
