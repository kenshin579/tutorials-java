import React, {Component} from 'react';
import styles from './Header.scss';
import {Navbar} from "react-bootstrap";
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

class Header extends Component {
    render() {
        return (
            <header>
                <Navbar expand="md" bg="dark" variant="dark">
                    <Navbar.Brand href="/">Quartz Schedulers</Navbar.Brand>
                </Navbar>
            </header>
        );
    }
}

export default Header;