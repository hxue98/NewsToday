import {
  ATTEMPT_AUTH,
  AUTH_SUCCESS,
  AUTH_FAIL,
} from './types';
import axios from 'axios';
import configuration from '../config'

const config = {
  headers: {
    'Content-Type': 'application/json',
  },
};

// Register User
export const register = (formData) => async (dispatch) => {
  dispatch({
    type: ATTEMPT_AUTH
  });
  try {
    const res = await axios.post(configuration.api + '/acc/register', formData, config);
    console.log('res', res);
    dispatch({
      type: AUTH_SUCCESS,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: AUTH_FAIL,
      payload: err.data,
    });
  }
};

// Login
export const login = (formData) => async (dispatch) => {
  console.log(formData);
  dispatch({
    type: ATTEMPT_AUTH
  });
  try {
    const res = await axios.post(configuration.api + '/acc/login', formData, config);
      dispatch({
        type: res.data.success ? AUTH_SUCCESS : AUTH_FAIL,
        payload: res.data,
      });
  } catch (err) {
    dispatch({
      type: AUTH_FAIL,
      payload: err.data,
    });
  }
}