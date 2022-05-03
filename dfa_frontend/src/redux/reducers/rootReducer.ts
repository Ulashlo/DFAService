import { combineReducers } from 'redux';
import { authReducer } from '@src/redux/reducers/auth/auth.reducer';
import { apiErrorReducer } from '@src/redux/reducers/apiError';
import { spinnerReducer } from '@src/redux/reducers/spinner';
import { dfasReducer } from '@src/redux/reducers/dfas';
import { balancesReducer } from '@src/redux/reducers/balances';
import { dfaViewsReducer } from '@src/redux/reducers/dfas/dfaViews.reducer';
import { requestsReducer } from '@src/redux/reducers/requests';
import { issuerRequestsReducer } from '@src/redux/reducers/issuerRequests';

export default combineReducers({
  auth: authReducer,
  apiError: apiErrorReducer,
  spinner: spinnerReducer,
  dfas: dfasReducer,
  balances: balancesReducer,
  dfaViews: dfaViewsReducer,
  requests: requestsReducer,
  issuerRequests: issuerRequestsReducer,
});
