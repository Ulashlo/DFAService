/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { DFAViewDto } from '@src/generated/backend';

export interface DataState {
  dfas: DFAViewDto[];
}

const initialState: DataState = {
  dfas: [],
};

const dataSlice = createSlice({
  name: 'data',
  initialState,
  reducers: {
    setDfas: (state, action: PayloadAction<DFAViewDto[]>) => {
      state.dfas = action.payload;
    },
  },
});

export const dataReducer = dataSlice.reducer;
export const { setDfas } = dataSlice.actions;
