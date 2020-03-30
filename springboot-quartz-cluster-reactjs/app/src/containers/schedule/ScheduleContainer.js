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
        if (prevProps.data.numOfAllJobs !== this.props.data.numOfAllJobs) {
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

    render() {
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
