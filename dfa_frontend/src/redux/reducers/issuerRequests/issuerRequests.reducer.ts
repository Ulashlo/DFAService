/* eslint no-param-reassign: off */
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { IssuerRequestDTO } from '@src/generated/backend';

export interface IssuerRequestsInfo {
  requests: IssuerRequestDTO[];
  isRequestsLoad: boolean;
}

export interface IssuerRequestsStateType {
  requestsInfo: IssuerRequestsInfo;
}

const initialState: IssuerRequestsStateType = {
  requestsInfo: {
    requests: [],
    isRequestsLoad: false,
  },
};

const dataSlice = createSlice({
  name: 'issuerRequests',
  initialState,
  reducers: {
    setIssuerRequests: (state, action: PayloadAction<IssuerRequestDTO[]>) => {
      state.requestsInfo.requests = action.payload;
      state.requestsInfo.isRequestsLoad = true;
    },
    deleteIssuerRequest: (state, action: PayloadAction<string>) => {
      state.requestsInfo.requests = state.requestsInfo.requests.filter((request) => request.id !== action.payload);
      state.requestsInfo.isRequestsLoad = true;
    },
    clearIssuerRequests: (state) => {
      state.requestsInfo.requests = [];
      state.requestsInfo.isRequestsLoad = false;
    },
  },
});

export const issuerRequestsReducer = dataSlice.reducer;
export const { setIssuerRequests, clearIssuerRequests, deleteIssuerRequest } = dataSlice.actions;
