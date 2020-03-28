import React from 'react';
import styles from './ScheduleItem.scss';
import classNames from 'classnames/bind';
import moment from "moment";
import {DATE_TIME_FORMAT} from "../../../constants";

const cx = classNames.bind(styles);

const ScheduleItem = ({schedule}) => {
    return (
        <tr>
            <td className="item-small">{schedule.jobName}</td>
            <td className="item-small">{schedule.groupName}</td>
            <td className="item-small">{moment(schedule.scheduleTime).format(DATE_TIME_FORMAT)}</td>
            <td className="item-small">{schedule.lastFiredTime != null ? moment(schedule.lastFiredTime).format(DATE_TIME_FORMAT) : 'N/A'}</td>
            <td className="item-small">{moment(schedule.nextFireTime).format(DATE_TIME_FORMAT)}</td>
            <td className="item-small">
                <button className="btn btn-secondary btn-sm item-xx-small">Delete</button>
            </td>
            <td className="item-small">{schedule.jobStatus}</td>
        </tr>
    )
};

export default ScheduleItem;
