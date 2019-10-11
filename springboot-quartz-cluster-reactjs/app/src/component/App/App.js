import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import MenuHeader from "../MenuHeader";
import QuartzScheduler from "../QuartzScheduler";
import SystemMonitor from "../SystemMonitor";

class App extends Component {
    render() {
        return (
            <Router>
                <>
                    <MenuHeader/>
                    <Switch>
                        <Route path="/" exact component={QuartzScheduler}/>
                        <Route path="/monitor" exact component={SystemMonitor}/>
                    </Switch>
                </>
            </Router>
        );
    }
}

export default App;
