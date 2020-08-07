import {
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  REGISTER_FAIL,
  REGISTER_SUCCESS,
  REMOVE_ALERT,
  LOGOUT,
  CLEAR_ERRORS,
} from '../actions/types';

const initialState = {
  token: localStorage.getItem('token'),
  isAuthenticating: null,
  loading: true,
  user: null,
  error: null,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case REGISTER_SUCCESS:
      localStorage.setItem('token', action.payload.token);
      return {
        ...state,
        ...action.payload,
        isAuthenticated: true,
        loading: false,
      };
    default:
      return state;
  }
};
