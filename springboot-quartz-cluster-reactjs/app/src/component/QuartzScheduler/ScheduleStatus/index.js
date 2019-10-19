import React, {Component} from 'react';
import StatusItem from "../StatusItem";
import {Row} from "react-bootstrap";

class ScheduleStatus extends Component {
    render() {
        const statusView = [];
        Object.keys(this.props.jobStatus).forEach((key) => {
            statusView.push(<StatusItem key={key} statusKey={key}>{this.props.jobStatus[key]}</StatusItem>)
        });

        return (
            <div className="mt-3 mb-4">
                <div className="h4 mb-3 text-gray-800">Job Status</div>
                <Row>
                    {statusView}
                </Row>
            </div>
        )
    }
}

export default ScheduleStatus