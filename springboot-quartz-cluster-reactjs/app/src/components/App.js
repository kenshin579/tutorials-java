import React from 'react';
import 'styles/base.scss';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {ListPage, NotFoundPage} from "../pages";
import BaseContainer from "../containers/common/BaseContainer";

function App() {
    return (
        <Router>
            <Switch>
                <Route path="/" exact component={ListPage}/>
                <Route component={NotFoundPage}/>
            </Switch>
            <BaseContainer/>
        </Router>
    );
}

export default App;
