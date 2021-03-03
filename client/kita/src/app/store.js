import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../features/counter/counterSlice';
import categoryReducer from '../features/category/categorySlice';
import userReducer from '../features/user/userSlice';

export default configureStore({
  reducer: {
    counter: counterReducer,
    categories: categoryReducer,
    user: userReducer
  },
});
