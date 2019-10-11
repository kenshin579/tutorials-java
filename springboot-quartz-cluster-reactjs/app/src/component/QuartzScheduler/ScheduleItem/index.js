import React, {Component} from 'react';
import moment from "moment";
import {DATE_TIME_FORMAT} from "../../../constants";

class ScheduleItem extends Component {

    render() {
        const {schedule, onDelete} = this.props;

        return (
            <tr>
                <td className="item-small">{schedule.jobName}</td>
                <td className="item-small">{moment(schedule.scheduleTime).format(DATE_TIME_FORMAT)}</td>
                <td className="item-small">{schedule.lastFiredTime != null ? moment(schedule.lastFiredTime).format(DATE_TIME_FORMAT) : 'N/A'}</td>
                <td className="item-small">{moment(schedule.nextFireTime).format(DATE_TIME_FORMAT)}</td>
                <td className="item-small">
                    <button className="btn btn-secondary btn-sm item-xx-small" onClick={() => onDelete(schedule.jobName)}>Delete</button>
                </td>
                <td className="item-small">{schedule.jobStatus}</td>
            </tr>
        )
    }
}

export default ScheduleItem