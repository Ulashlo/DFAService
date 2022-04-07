/* eslint no-param-reassign: off */
import { LocalStorageService } from '@src/services/LocalStorageService';
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface AuthInfo {
  token: string;
  username: string;
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
    clearAuthInfo: (state) => {
      state.authInfo = undefined;
      LocalStorageService.authInfo().set(undefined);
    },
  },
});

export const authReducer = authSlice.reducer;
export const { setAuthInfo, clearAuthInfo } = authSlice.actions;
