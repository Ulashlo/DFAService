/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { AppPromiseThunk } from '@src/redux/storeFactory';

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

export function doWithSpinner<ReturnType>(
  asyncFunc: () => Promise<ReturnType>,
  message?: string,
): AppPromiseThunk<ReturnType> {
  return async (dispatch) => {
    dispatch(showSpinner(message));
    try {
      return await asyncFunc();
    } finally {
      dispatch(hideSpinner());
    }
  };
}

export async function doWithLocalSpinner<ReturnType>(
  asyncFunc: () => Promise<ReturnType>,
  params: { showSpinner: () => void; hideSpinner: () => void },
): Promise<ReturnType> {
  params.showSpinner();
  try {
    return await asyncFunc();
  } finally {
    params.hideSpinner();
  }
}
