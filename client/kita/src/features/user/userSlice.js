import {
  createAsyncThunk,
  createEntityAdapter,
  createSlice,
} from "@reduxjs/toolkit";
import axios from "axios";
export const fetchUsers = createAsyncThunk("Users/fetchUsers", async (data) => {
  const payload = await axios({
    method: "POST",
    url: "http://localhost:8080/api/auth",
    data,
    // headers: {
    //   Authorization: `Bearer 123`,
    // },
    headers: {
      Public: "public",
    },
  })
    .then((response) => {
      // setLoading(false);
      console.log(response.data);
      return response.data;
    })
    .catch((err) => {
      console.log(err);
    });
  return payload;
});
export const userSlice = createSlice({
  name: "Users",
  initialState: {
    data: {}
  },
  reducers: {
    //  fetch(state, action){
    //    const {data} = action.payload
    //  }
  },
  extraReducers: {
    // omit other reducers
    [fetchUsers.pending]: (state, action) => {
      state.status = "loading";
    },

    [fetchUsers.fulfilled]: (state, action) => {
      state.status = "completed";
      state.data = action.payload;
    },
  },
});

export const { increment, decrement, incrementByAmount } = userSlice.actions;
//   export const {
//     selectAll: selectAlluser,
//     selectById: selectuserById,
//   } = userAdapter.getSelectors((state) => state.Users);

export default userSlice.reducer;
