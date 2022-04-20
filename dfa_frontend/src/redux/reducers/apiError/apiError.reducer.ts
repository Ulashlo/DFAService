/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { ApiError } from '@src/generated/backend';

export interface ApiErrorInfo {
  info?: ApiError;
  shown: boolean;
}

export interface ApiErrorStateType {
  apiErrorInfo?: ApiErrorInfo;
}

const initialState: ApiErrorStateType = {
  apiErrorInfo: {
    info: undefined,
    shown: false,
  },
};

const apiErrorSlice = createSlice({
  name: 'apiError',
  initialState,
  reducers: {
    setApiErrorInfo: (state, action: PayloadAction<ApiErrorInfo>) => {
      state.apiErrorInfo = action.payload;
    },
    clearApiErrorInfo: (state) => {
      const { apiErrorInfo } = state;
      if (apiErrorInfo) {
        apiErrorInfo.shown = false;
      }
    },
  },
});

export const apiErrorReducer = apiErrorSlice.reducer;
export const { setApiErrorInfo, clearApiErrorInfo } = apiErrorSlice.actions;
