import React, {Component} from 'react';
import styles from './Status.scss';
import classNames from 'classnames/bind';
import {Card, Col} from "react-bootstrap";
import {STATUS_KEY_TITLE} from "../../../constants";

const cx = classNames.bind(styles);

class Status extends Component {

    render() {
        return (
            <Col xs={3} className={cx('ml-4', 'mb-4')}>
                <Card>
                    <Card.Body className={cx('border-left-primary', 'shadow')}>
                        <Card.Title className={cx('text-uppercase')}>
                            {STATUS_KEY_TITLE[this.props.statusKey]}
                        </Card.Title>
                        <Card.Text className={cx('h3', 'font-weight-bold', 'text-gray-800')}>
                            {this.props.children}
                        </Card.Text>
                    </Card.Body>
                </Card>
            </Col>
        )
    }
}

export default Status;
