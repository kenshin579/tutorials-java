import React, {Component} from 'react';
import {MAX_INTERVAL_SECONDS} from "../../constants";
import SchedulerService from "../../service/SchedulerService";

class QuartzScheduler extends Component {
    componentDidMount() {
        this.refreshSchedules();
        this.interval = setInterval(() => this.refreshSchedules(), MAX_INTERVAL_SECONDS);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    refreshSchedules() {
        SchedulerService.getAllJobs()
            .then(response => {
                    console.log('get all jobs :: response', response);
                    this.setState({schedulers: response.data.data.result.jobs})
                }
            ).catch(error => {
                console.error('error occurred while getting all jobs', error);
            }
        );
    }

    render() {
        return (
            <div className="container-fluid">
                {/*<ScheduleStatus/>*/}
                {/*<ScheduleList/>*/}
            </div>
        )
    }
}

export default QuartzScheduler