import React, {Component} from 'react';
import {Card, Col} from "react-bootstrap";
import {STATUS_KEY_TITLE} from "../../../constants";
import './ScheduleStatus.css';

class StatusItem extends Component {
    render() {
        return (
            <Col xs={3} className="ml-4 mb-4">
                <Card>
                    <Card.Body className="border-left-primary shadow">
                        <Card.Title className="text-uppercase">
                            {STATUS_KEY_TITLE[this.props.statusKey]}
                        </Card.Title>
                        <Card.Text className="h3 font-weight-bold text-gray-800">
                            {this.props.children}
                        </Card.Text>
                    </Card.Body>
                </Card>
            </Col>
        )
    }
}

export default StatusItem