import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as baseActions from 'store/modules/base';
import JobList from "../../components/schedule/JobList";

class JobListContainer extends Component {
    handleModal = (jobName, groupName) => {
        const {BaseActions} = this.props;
        BaseActions.showModal('deleteJob');
        BaseActions.updateDeleteJobModal({
            jobName: jobName,
            groupName: groupName
        });
    };

    render() {
        const {data} = this.props;
        const {handleModal} = this;

        return (
            <JobList
                jobs={data.jobs}
                onDeleteModal={handleModal}
            />
        );
    }
}

export default connect(
    (state) => ({
        data: state.list.get('data')
    }),
    (dispatch) => ({
        BaseActions: bindActionCreators(baseActions, dispatch),
    })
)(JobListContainer);
