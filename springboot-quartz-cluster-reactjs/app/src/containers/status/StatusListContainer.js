import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux'
import * as scheduleListActions from 'store/modules/list';

class ScheduleListContainer extends Component {
    componentDidMount() {
        this.loadData();
    }

    componentDidUpdate(prevProps, prevState) {
        //이전 number와 현재 number가 다르면 요청을 시작함
        if (this.props.number !== prevProps.number) {
            this.loadData();
        }
    }

    loadData = () => {
        const {ScheduleListActions} = this.props;

        ScheduleListActions.getSchedule().then(
            (response) => {
                console.log('loadData:response', response);
            }
        ).catch(
            (error) => {
                console.log('loadData:error', error);
            }
        );
    };

    render() {
        return (
            <div>
                ScheduleListContainer
            </div>
        );
    }
}

export default connect(
    (state) => ({
        data: state.list.data,
        loading: state.list.pending,
        error: state.list.error
    }),
    (dispatch) => ({
        ScheduleListActions: bindActionCreators(scheduleListActions, dispatch)
    })
)(ScheduleListContainer);
