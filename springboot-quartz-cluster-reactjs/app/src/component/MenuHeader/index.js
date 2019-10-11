import React, {Component} from 'react';
import {Navbar, Nav} from "react-bootstrap";

class MenuHeader extends Component {
    render() {
        return (
            <header>
                <Navbar expand="md" bg="dark" variant="dark">
                    <Navbar.Brand href="/">Quartz Schedulers</Navbar.Brand>
                    <Nav>
                        <Nav.Link href="/monitor">Systems Monitor </Nav.Link>
                    </Nav>
                </Navbar>
            </header>
        )
    }
}

export default MenuHeader