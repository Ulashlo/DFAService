/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { DFAViewDto } from '@src/generated/backend';
import { LocalStorageService } from '@src/services/LocalStorageService';

export interface DataInfo {
  dfas: DFAViewDto[];
  idDfasLoad: boolean;
}

export interface DataStateType {
  dataInfo: DataInfo;
}

const initialState: DataStateType = {
  dataInfo: LocalStorageService.dataInfo().get(),
};

const dataSlice = createSlice({
  name: 'data',
  initialState,
  reducers: {
    setDfas: (state, action: PayloadAction<DFAViewDto[]>) => {
      state.dataInfo.dfas = action.payload;
      state.dataInfo.idDfasLoad = true;
      LocalStorageService.dataInfo().set({ ...state.dataInfo, dfas: action.payload, idDfasLoad: true });
    },
  },
});

export const dataReducer = dataSlice.reducer;
export const { setDfas } = dataSlice.actions;
