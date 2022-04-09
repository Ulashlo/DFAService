/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface SpinnerState {
  shown: boolean;
  message?: string;
}

const initialState: SpinnerState = {
  shown: false,
  message: undefined,
};

const spinnerSlice = createSlice({
  name: 'spinner',
  initialState,
  reducers: {
    showSpinner: (state, action: PayloadAction<string | undefined>) => {
      state.shown = true;
      state.message = action.payload;
    },
    hideSpinner: (state) => {
      state.shown = false;
    },
  },
});

export const spinnerReducer = spinnerSlice.reducer;
export const { showSpinner, hideSpinner } = spinnerSlice.actions;
