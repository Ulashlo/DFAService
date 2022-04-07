import { configureStore } from '@reduxjs/toolkit';
import rootReducer from '@src/redux/reducers/rootReducer';

const storeFactory = () => configureStore<RootStateType>({ reducer: rootReducer });

export type RootStateType = ReturnType<typeof rootReducer>;

export default storeFactory;
