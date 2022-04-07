import { combineReducers } from 'redux';
import { authReducer } from '@src/redux/reducers/auth/auth.reducer';

export default combineReducers({
  auth: authReducer,
});
