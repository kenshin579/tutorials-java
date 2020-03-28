import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as scheduleListActions from 'store/modules/list';
import StatusList from "../../components/schedule/StatusList";
import JobList from "../../components/schedule/JobList";
import {MAX_INTERVAL_SECONDS} from "../../constants";

class ScheduleContainer extends Component {
    componentDidMount() {
        this.loadData();
        this.interval = setInterval(() => this.loadData(), MAX_INTERVAL_SECONDS);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
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
            <div>
                <StatusList jobStatus={jobStatus}/>
                <JobList jobs={data.jobs}/>
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
