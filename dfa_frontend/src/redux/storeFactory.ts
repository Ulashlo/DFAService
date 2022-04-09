import { Action, configureStore, ThunkAction } from '@reduxjs/toolkit';
import rootReducer from '@src/redux/reducers/rootReducer';

const storeFactory = () => configureStore<RootStateType>({ reducer: rootReducer });

export type RootStateType = ReturnType<typeof rootReducer>;

export type AppThunk<T = void> = ThunkAction<T, RootStateType, void, Action>;

export type AppPromiseThunk<T> = AppThunk<Promise<T>>;

export default storeFactory;
