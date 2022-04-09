import { combineReducers } from 'redux';
import { authReducer } from '@src/redux/reducers/auth/auth.reducer';
import { apiErrorReducer } from '@src/redux/reducers/apiError';

export default combineReducers({
  auth: authReducer,
  apiError: apiErrorReducer,
});
