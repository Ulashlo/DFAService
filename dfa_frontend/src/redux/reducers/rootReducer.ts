import { combineReducers } from 'redux';
import { authReducer } from '@src/redux/reducers/auth/auth.reducer';
import { apiErrorReducer } from '@src/redux/reducers/apiError';
import { spinnerReducer } from '@src/redux/reducers/spinner';
import { dataReducer } from '@src/redux/reducers/data';

export default combineReducers({
  auth: authReducer,
  apiError: apiErrorReducer,
  spinner: spinnerReducer,
  data: dataReducer,
});
