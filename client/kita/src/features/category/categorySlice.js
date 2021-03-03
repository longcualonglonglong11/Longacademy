import {
  createAsyncThunk,
  createEntityAdapter,
  createSlice,
} from "@reduxjs/toolkit";
import axios from "axios";
const categoryAdapter = createEntityAdapter();
export const fetchCategories = createAsyncThunk(
  "categories/fetchCategories",
  async () => {
    const data = await axios({
      method: "GET",
      url: "http://localhost:8080/api/category",
      // headers: {
      //   Authorization: `Bearer 123`,
      // },
      headers:  {
        Public: "public",
      } 
    })
      .then((response) => {
        // setLoading(false);
        return response.data;
      })
      .catch((err) => {
        console.log(err);
      });
    return data;
  }
);
export const categorySlice = createSlice({
  name: "categories",
  initialState: categoryAdapter.getInitialState({
    status: 'idle'
  }),
  reducers: {
    //  fetch(state, action){
    //    const {data} = action.payload
    //  }
  },
  extraReducers: {
    // omit other reducers
    [fetchCategories.pending]: (state, action) => {
      state.status = 'loading'
      console.log(state.status)
    },

    [fetchCategories.fulfilled]: (state, action) => {
      state.status = 'completed'
      categoryAdapter.upsertMany(state, action.payload);
    },
  },
});

export const {
  increment,
  decrement,
  incrementByAmount,
} = categorySlice.actions;
export const {
  selectAll: selectAllCategory,
  selectById: selectCategoryById,
} = categoryAdapter.getSelectors((state) => state.categories);

export default categorySlice.reducer;
