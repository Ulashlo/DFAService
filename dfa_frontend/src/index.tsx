import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import storeFactory from '@src/redux/storeFactory';

const store = storeFactory();

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store} />
  </React.StrictMode>,
  document.getElementById('root'),
);

export type AppDispatch = typeof store.dispatch;
