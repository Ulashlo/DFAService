import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import storeFactory from './redux/storeFactory';
import { CustomRouter } from './CustomRouter';

const store = storeFactory();

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <CustomRouter />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root'),
);

export type AppDispatch = typeof store.dispatch;
