import React, {Component} from 'react';
import SchedulerService from '../../../service/SchedulerService';
import StatusItem from "../StatusItem";
import {MAX_INTERVAL_SECONDS} from "../../../constants";
import {Row} from "react-bootstrap";


class ScheduleStatus extends Component {
    constructor(props) {
        super(props);
        this.state = {
            jobStatus: {}
        };
        this.loadStatusInfo = this.loadStatusInfo.bind(this);
    }

    loadStatusInfo() {
        SchedulerService.getStatus()
            .then(response => {
                this.setState({
                    jobStatus: response.data.data,
                });
            }).catch(error => {
            console.error('error occurred while getting status info', error);
        })
    }

    componentDidMount() {
        this.loadStatusInfo();
        this.interval = setInterval(() => this.loadStatusInfo(), MAX_INTERVAL_SECONDS);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    render() {
        const statusView = [];

        Object.keys(this.state.jobStatus).forEach((key) => {
            statusView.push(<StatusItem key={key} statusKey={key}>{this.state.jobStatus[key]}</StatusItem>)
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