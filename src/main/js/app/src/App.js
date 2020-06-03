import React, { Component } from 'react';
import './App.css';
import ListManager from './components/ListManager';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {

  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={ListManager}/>
          </Switch>
        </Router>
    )
  }
}

export default App;