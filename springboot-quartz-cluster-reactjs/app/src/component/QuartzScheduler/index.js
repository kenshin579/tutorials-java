import React, {Component} from 'react';
import {DELAY_TIME_FOR_MESSAGE, MAX_INTERVAL_SECONDS} from "../../constants";
import SchedulerService from "../../service/SchedulerService";
import ScheduleStatus from "./ScheduleStatus";
import ScheduleList from "./ScheduleList";

class QuartzScheduler extends Component {
    constructor(props) {
        super(props);
        this.deleteScheduleOnClick = this.deleteScheduleOnClick.bind(this);
        this.addScheduleOnSubmit = this.addScheduleOnSubmit.bind(this);
        this.refreshSchedules = this.refreshSchedules.bind(this);
        this.hideModal = this.hideModal.bind(this);
        this.showModal = this.showModal.bind(this);
        this.hideNotification = this.hideNotification.bind(this);
        this.showNotification = this.showNotification.bind(this);
    }

    state = {
        schedules: [],
        newSchedule: {},
        jobStatus: {},
        message: null,
        enableModal: false,
        enableNotification: false,
        visible: false,
    };

    deleteScheduleOnClick(jobName, groupName) {
        SchedulerService.deleteJob(jobName, groupName)
            .then(response => {
                    console.log('deleteJob response', response);
                    this.setState({message: `${jobName}-${groupName}을 성공적으로 삭제하였습니다.`});
                }
            ).catch(error => {
            let responseMsg = JSON.parse(error.request.response);
            this.setState({message: `${jobName}-${groupName} - ${responseMsg.message}`});
            console.error('error occurred while deleting the job - ', responseMsg.message, error);
        }).then(() => {
                this.refreshSchedules();
                this.showNotification();
            }
        );
    }

    addScheduleOnSubmit(e) {
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);
        const jobName = formData.get('jobName');
        const groupName = formData.get('groupName');
        const cronExpression = formData.get('cronExpression');
        const startDateAt = formData.get('startDateAt');
        const repeatIntervalInSeconds = formData.get('repeatIntervalInSeconds');
        const repeatCount = formData.get('repeatCount');

        //todo: 인자가 너무 많다. 객체로 넘기던지 해야 할 듯하다
        SchedulerService.addJob(jobName, groupName, cronExpression, startDateAt, repeatIntervalInSeconds, repeatCount)
            .then(response => {
                    this.setState({message: `${jobName}-${groupName} 성공적으로 추가되었습니다.`});
                }
            ).catch(error => {
            let responseMsg = JSON.parse(error.request.response);
            this.setState({message: `${jobName}-${groupName} - ${responseMsg.message}`});
            console.error('error occurred while adding the job - ', responseMsg.message, error);
        }).then(() => {
                this.refreshSchedules();
                this.showNotification();
            }
        );
        this.hideModal();
    }

    hideModal() {
        this.setState({enableModal: false});
    }

    showModal() {
        console.log('showModal');
        this.setState({enableModal: true});
    }

    hideNotification() {
        this.setState({enableNotification: false});
    }

    showNotification() {
        this.setState({enableNotification: true}, () => {
            window.setTimeout(() => {
                this.setState({enableNotification: false})
            }, DELAY_TIME_FOR_MESSAGE);
        });
    }

    refreshSchedules() {
        console.log('refreshSchedules');
        SchedulerService.getAllJobs()
            .then(response => {
                    console.log('get all jobs :: response', response);
                    this.setState({schedules: response.data.jobs});
                    this.setState({jobStatus: response.data.stats});
                }
            ).catch(error => {
                console.error('error occurred while getting all jobs', error);
            }
        );
    }

    componentDidMount() {
        this.refreshSchedules();
        this.interval = setInterval(() => this.refreshSchedules(), MAX_INTERVAL_SECONDS);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    render() {
        const {schedules, jobStatus, message, enableNotification, enableModal} = this.state;
        const {deleteScheduleOnClick, addScheduleOnSubmit, hideModal, showModal, hideNotification, showNotification} = this;
        return (
            <div className="container-fluid">
                <ScheduleStatus jobStatus={jobStatus}/>
                <ScheduleList schedules={schedules}
                              message={message}
                              enableNotification={enableNotification}
                              enableModal={enableModal}
                              onDelete={deleteScheduleOnClick}
                              onSubmit={addScheduleOnSubmit}
                              hideModal={hideModal}
                              showModal={showModal}
                              hideNotification={hideNotification}
                              showNotification={showNotification}
                />
            </div>
        )
    }
}

export default QuartzScheduler