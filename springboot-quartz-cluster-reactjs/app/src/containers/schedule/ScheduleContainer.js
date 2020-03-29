import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as scheduleListActions from 'store/modules/list';
import StatusList from "../../components/schedule/StatusList";
import {MAX_INTERVAL_SECONDS} from "../../constants";
import classNames from "classnames/bind";
import styles from "../../components/schedule/JobList/JobList.scss";
import JobListContainer from "../job/JobListContainer";

const cx = classNames.bind(styles);

class ScheduleContainer extends Component {
    componentDidMount() {
        this.loadData();
        this.interval = setInterval(() => this.loadData(), MAX_INTERVAL_SECONDS);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    componentDidUpdate(prevProps, prevState) {
        // 페이지/태그가 바뀔 때 리스트를 다시 불러옵니다.
        if (prevProps.data.jobs.length !== this.props.data.jobs.length) {
            this.loadData();
        }
    }

    loadData = async () => {
        const {ScheduleListActions} = this.props;
        try {
            const response = await ScheduleListActions.getScheduleInfo();
            console.log('response', response);
        } catch (e) {
            console.error(e);
        }
    };

    handleAddJob = (e) => {
        console.log('submit');
        // e.preventDefault();
        // const form = e.target;
        // const formData = new FormData(form);
        // const jobName = formData.get('jobName');
        // const groupName = formData.get('groupName');
        // const cronExpression = formData.get('cronExpression');
        // const startDateAt = formData.get('startDateAt');
        // const repeatIntervalInSeconds = formData.get('repeatIntervalInSeconds');
        // const repeatCount = formData.get('repeatCount');
        //
        // //todo: 인자가 너무 많다. 객체로 넘기던지 해야 할 듯하다
        // SchedulerService.addJob(jobName, groupName, cronExpression, startDateAt, repeatIntervalInSeconds, repeatCount)
        //     .then(response => {
        //             this.setState({message: `${jobName}-${groupName} 성공적으로 추가되었습니다.`});
        //         }
        //     ).catch(error => {
        //     let responseMsg = JSON.parse(error.request.response);
        //     this.setState({message: `${jobName}-${groupName} - ${responseMsg.message}`});
        //     console.error('error occurred while adding the job - ', responseMsg.message, error);
        // }).then(() => {
        //         this.refreshSchedules();
        //         this.showNotification();
        //     }
        // );
        // this.hideModal();
    };

    render() {
        const {handleDeleteJob, handleAddJob} = this;

        const {data, loading} = this.props;
        if (loading) {
            return null;
        }
        let jobStatus = {
            'numOfAllJobs': data.numOfAllJobs,
            'numOfGroups': data.numOfGroups,
            'numOfRunningJobs': data.numOfRunningJobs
        };

        return (
            <div className={cx('container-fluid')}>
                <StatusList jobStatus={jobStatus}/>
                <JobListContainer/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        data: state.list.get('data'),
        loading: state.pender.pending['list/GET_SCHEDULE_INFO']
    }),
    (dispatch) => ({
        ScheduleListActions: bindActionCreators(scheduleListActions, dispatch)
    })
)(ScheduleContainer);
