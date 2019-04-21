import React, {Component} from 'react'
import SchedulerService from '../service/SchedulerService';
import {DATE_TIME_FORMAT} from '../constants';
import moment from 'moment';

class ListSchedulersComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            schedulers: [],
            message: null
        };
        this.deleteScheduleClicked = this.deleteScheduleClicked.bind(this);
        this.addScheduleClicked = this.addScheduleClicked.bind(this);
        this.refreshSchedules = this.refreshSchedules.bind(this);
    }

    componentDidMount() {
        this.refreshSchedules();
        // this.interval = setInterval(() => this.refreshSchedules(), 5000);
    }

    componentWillUnmount() {
        // clearInterval(this.interval);
    }

    refreshSchedules() {
        console.log('refreshSchedules');

        SchedulerService.getJobs()
            .then(
                response => {
                    console.log('response', response);
                    this.setState({schedulers: response.data.data})
                }
            )
    }

    deleteScheduleClicked(jobName) {
        SchedulerService.deleteJob(jobName)
            .then(
                response => {
                    console.log('jobName', jobName, 'response', response);
                    this.setState({message: `Delete of Job Scheduler ${jobName} Successful`});
                    this.refreshSchedules()
                }
            )

    }

    addScheduleClicked() {
        this.props.history.push(`/schedulers/-1`)
    }

    render() {
        console.log('render');
        return (
            <div className="container">
                <h3>All Job Schedules </h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table table-striped table-sm">
                        <thead className="thread-light">
                        <tr>
                            <th scope="col">Job Name</th>
                            <th scope="col">Job Schedule Time</th>
                            <th scope="col">Job Last Fired Time</th>
                            <th scope="col">Job Next Fired Time</th>
                            <th scope="col">Action</th>
                            <th scope="col">Job Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.schedulers.map(
                                schedule =>
                                    <tr key={schedule.jobName}>
                                        <td>{schedule.jobName}</td>
                                        <td>{moment(schedule.scheduleTime).format(DATE_TIME_FORMAT)}</td>
                                        <td>{moment(schedule.lastFiredTime).format(DATE_TIME_FORMAT)}</td>
                                        <td>{moment(schedule.nextFireTime).format(DATE_TIME_FORMAT)}</td>
                                        <td>
                                            <button className="btn btn-primary disabled">Edit Job</button>
                                            |
                                            <button className="btn btn-primary disabled">Delete Job</button>
                                        </td>
                                        <td>{schedule.jobStatus}</td>
                                        {/*<td>*/}
                                        {/*    <button className="btn btn-success" onClick={() => this.updateCourseClicked(course.id)}>Update</button>*/}
                                        {/*</td>*/}
                                        {/*<td>*/}
                                        {/*    <button className="btn btn-warning" onClick={() => this.deleteSchedulerClicked(course.id)}>Delete</button>*/}
                                        {/*</td>*/}
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListSchedulersComponent