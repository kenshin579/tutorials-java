import React, {Component} from 'react';
import {Navbar} from "react-bootstrap";

class MenuHeader extends Component {
    render() {
        return (
            <header>
                <Navbar expand="md" bg="dark" variant="dark">
                    <Navbar.Brand href="/">Quartz Schedulers</Navbar.Brand>
                </Navbar>
            </header>
        )
    }
}

export default MenuHeader