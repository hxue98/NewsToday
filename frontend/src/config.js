import { getJwtToken } from './store';

const configuration = {
  api: "http://localhost:8080",
  axiosConfig: {
    headers: {
      'Content-Type': 'application/json',
    }
  },
};

export const getAuthorizedConfig = () => {
  let config = configuration.axiosConfig;
  config.headers['Authorization'] = getJwtToken();
  return config;
}

export default configuration;