import React from 'react';
import styles from './PageTemplate.scss';
import classNames from 'classnames/bind';
import Header from "../common/Header";

const cx = classNames.bind(styles);

const PageTemplate = ({children}) => (
    <div>
        <Header/>
        <div>
            {children}
        </div>
    </div>
);

export default PageTemplate;
