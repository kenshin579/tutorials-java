import React from 'react';
import styles from './JobItem.scss';
import classNames from 'classnames/bind';
import moment from "moment";
import {DATE_TIME_FORMAT} from "../../../constants";

const cx = classNames.bind(styles);

const JobItem = ({job, onDeleteModal}) => {
    return (
        <tr>
            <td className="item-small">{job.jobName}</td>
            <td className="item-small">{job.groupName}</td>
            <td className="item-small">{moment(job.scheduleTime).format(DATE_TIME_FORMAT)}</td>
            <td className="item-small">{job.lastFiredTime != null ? moment(job.lastFiredTime).format(DATE_TIME_FORMAT) : 'N/A'}</td>
            <td className="item-small">{moment(job.nextFireTime).format(DATE_TIME_FORMAT)}</td>
            <td className="item-small">
                <button className="btn btn-secondary btn-sm item-xx-small"
                        onClick={() => onDeleteModal(job.jobName, job.groupName)}>Delete
                </button>
            </td>
            <td className="item-small">{job.jobStatus}</td>
        </tr>
    )
};

export default JobItem;
