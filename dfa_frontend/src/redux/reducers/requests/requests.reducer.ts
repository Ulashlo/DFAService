/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { AllRequestsDTO } from '@src/generated/backend';

export interface RequestsInfo {
  requests: AllRequestsDTO[];
  idRequestsLoad: boolean;
}

export interface RequestsStateType {
  requestsInfo: RequestsInfo;
}

const initialState: RequestsStateType = {
  requestsInfo: {
    requests: [],
    idRequestsLoad: false,
  },
};

const dataSlice = createSlice({
  name: 'requests',
  initialState,
  reducers: {
    setRequests: (state, action: PayloadAction<AllRequestsDTO[]>) => {
      state.requestsInfo.requests = action.payload;
      state.requestsInfo.idRequestsLoad = true;
    },
  },
});

export const requestsReducer = dataSlice.reducer;
export const { setRequests } = dataSlice.actions;
