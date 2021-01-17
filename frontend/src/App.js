import React, { Component } from 'react';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom'
import Summary from './component/Summary'
import Navbar from './component/Navbar'

function App() {
  return (
    <div>
      <Navbar/>
      <Router>
        <Switch>
          <Route path='/' exact={true} component = {Summary} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
