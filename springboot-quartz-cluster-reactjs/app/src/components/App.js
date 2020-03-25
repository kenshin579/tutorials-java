import React from 'react';
import 'styles/base.scss';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {ListPage, NotFoundPage} from "../pages";

function App() {
    return (
        <Router>
            <Switch>
                <Route path="/" exact component={ListPage}/>
                <Route component={NotFoundPage}/>
            </Switch>
        </Router>
    );
}

export default App;
