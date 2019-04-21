import React, { Component } from 'react';
import { Link } from 'react-router-dom'

class MenuComponent extends Component {
    render() {
        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a href="/" className="navbar-brand">Quartz Schedulers</a></div>
                    <ul className="navbar-nav">
                        <li><Link className="nav-link" to="">Systems Monitor</Link></li>
                    </ul>
                </nav>
            </header>
        )
    }
}

export default MenuComponent