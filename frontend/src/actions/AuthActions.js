import {
  ATTEMPT_AUTH,
  AUTH_SUCCESS,
  AUTH_FAIL,
} from './types';
import axios from 'axios';
import configuration from '../config';

// Register User
export const register = (formData) => async (dispatch) => {
  dispatch({
    type: ATTEMPT_AUTH
  });
  try {
    const res = await axios.post(configuration.api + '/acc/register', formData, configuration.axiosConfig);
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
  dispatch({
    type: ATTEMPT_AUTH
  });
  try {
    const res = await axios.post(configuration.api + '/acc/login', formData, configuration.axiosConfig);
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