import React from 'react';
import Navbar from '../src/components/layout/Navbar';
import Login from '../src/components/auth/Login';
import Register from '../src/components/auth/Register';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';

function App() {
  return (
    <Router>
      <Navbar />
      <div className='App'>
        <Switch>
          <Route exact path='/Login' component={Login} />
          <Route exact path='/Register' component={Register} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
