import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import MenuHeader from "../MenuHeader";
import QuartzScheduler from "../QuartzScheduler";

class App extends Component {
    render() {
        return (
            <Router>
                <>
                    <MenuHeader/>
                    <Switch>
                        <Route path="/" exact component={QuartzScheduler}/>
                    </Switch>
                </>
            </Router>
        );
    }
}

export default App;
