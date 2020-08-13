import {
  ATTEMPT_AUTH,
  AUTH_SUCCESS,
  AUTH_FAIL,
  ATTEMPT_LOGOUT,
  LOGOUT_SUCCESS,
  LOGOUT_FAIL,
} from '../actions/types';

const initialState = {
  token: localStorage.getItem('jwtToken'),
  loading: false,
  error: null,
  isLoggedIn: false,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case ATTEMPT_AUTH:
      return {
        ...state,
        loading: true,
      };
    case AUTH_SUCCESS:
      localStorage.setItem('jwtToken', action.payload.jwtToken);
      return {
        ...state,
        token: action.payload.jwtToken,
        loading: false,
        isLoggedIn: true,
      };
    case AUTH_FAIL:
      return {
        ...state,
        token: null,
        loading: false,
        error: action.payload.msg,
      };
    case ATTEMPT_LOGOUT:
      return {
        ...state,
        loading: true,
      };
    case LOGOUT_SUCCESS:
      localStorage.removeItem('jwtToken');
      return {
        ...state,
        token: null,
        loading: false,
        isLoggedIn: false,
      };
    case LOGOUT_FAIL:
      return {
        ...state,
        loading: false,
        error: "Logout failed",
      };
    default:
      return state;
  }
};
