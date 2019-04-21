import React, {Component} from 'react';
import ListSchedulersComponent from './ListSchedulersComponent';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import MenuComponent from "./MenuComponent";
import EditSchedulerComponent from "./EditSchedulerComponent";

class SchedulerApp extends Component {
    render() {
        return (
            <Router>
                <>
                    <MenuComponent/>
                    <EditSchedulerComponent/>
                    <Switch>
                        <Route path="/" exact component={ListSchedulersComponent}/>
                        <Route path="/scheduler" exact component={ListSchedulersComponent}/>
                        {/*<Route path="/scheduler/:id" exact component={SchedulerComponent} />*/}
                    </Switch>
                </>
            </Router>
        )
    }
}

export default SchedulerApp