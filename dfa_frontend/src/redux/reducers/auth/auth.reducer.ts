/* eslint no-param-reassign: off */
import { LocalStorageService } from '@src/services/LocalStorageService';
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface AuthInfo {
  token: string;
  username: string;
  address?: string;
  roles: string[];
}

export interface AuthStateType {
  authInfo?: AuthInfo;
}

const initialState: AuthStateType = {
  authInfo: LocalStorageService.authInfo().get(),
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setAuthInfo: (state, action: PayloadAction<AuthInfo>) => {
      state.authInfo = action.payload;
      LocalStorageService.authInfo().set(action.payload);
    },
    setAddressAuthInfo: (state, action: PayloadAction<string>) => {
      if (state.authInfo) {
        state.authInfo.address = action.payload;
      }
      LocalStorageService.authInfo().set(state.authInfo);
    },
    clearAuthInfo: (state) => {
      state.authInfo = undefined;
      LocalStorageService.authInfo().set(undefined);
    },
  },
});

export const authReducer = authSlice.reducer;
export const { setAuthInfo, clearAuthInfo, setAddressAuthInfo } = authSlice.actions;
