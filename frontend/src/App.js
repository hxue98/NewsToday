import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './store';

import './App.css';

import Navbar from './components/layout/Navbar';
import Login from './components/auth/Login';
import Register from './components/auth/Register';
import News from './components/pages/News';

function App() {
  return (
    <Provider store={store}>
      <Router>
        <Navbar />
        <div className='App'>
          <Switch>
            <Route exact path='/Login' component={Login} />
            <Route exact path='/Register' component={Register} />
            <Route exact path='/News/:id' component={News} />
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
