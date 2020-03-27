import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as scheduleListActions from 'store/modules/list';
import StatusList from "../../components/schedule/StatusList";
import ScheduleList from "../../components/schedule/ScheduleList";
import {MAX_INTERVAL_SECONDS} from "../../constants";

class ScheduleContainer extends Component {
    componentDidMount() {
        this.loadData();
        this.interval = setInterval(() => this.loadData(), MAX_INTERVAL_SECONDS);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    componentDidUpdate(prevProps, prevState) {
        //이전 number와 현재 number가 다르면 요청을 시작함
        if (this.props.number !== prevProps.number) {
            this.loadData();
        }
    }

    loadData = async () => {
        const {ScheduleListActions} = this.props;

        try {
            const response = await ScheduleListActions.getSchedule();
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
        console.log('props', this.props);
        let jobStatus = {
            'numOfAllJobs': data.numOfAllJobs,
            'numOfGroups': data.numOfGroups,
            'numOfRunningJobs': data.numOfRunningJobs
        };

        return (
            <div>
                <StatusList jobStatus={jobStatus}/>
                <ScheduleList/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        data: state.list.get('data'),
        loading: state.list.get('pending'),
        error: state.list.get('error')
    }),
    (dispatch) => ({
        ScheduleListActions: bindActionCreators(scheduleListActions, dispatch)
    })
)(ScheduleContainer);
