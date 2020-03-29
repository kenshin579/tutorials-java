import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as baseActions from 'store/modules/base';
import * as jobActions from 'store/modules/job';
import {withRouter} from 'react-router-dom';
import DeleteJobModal from "../../components/modal/DeleteJobModal";

class DeleteJobModalContainer extends Component {
    handleCancel = () => {
        const {BaseActions} = this.props;
        BaseActions.hideModal('deleteJob');
        BaseActions.updateDeleteJobModal({
            jobName: '',
            groupName: ''
        });
    };

    handleConfirm = async () => {
        const {BaseActions, JobActions, jobName, groupName, history} = this.props;

        try {
            // 삭제 후, 모달 닫고 홈페이지로 이동
            await JobActions.deleteJob({jobName, groupName});
            BaseActions.hideModal('deleteJob');
            BaseActions.updateDeleteJobModal({
                jobName: '',
                groupName: ''
            });
            history.push('/');
        } catch (e) {
            console.log(e);
        }
    };

    render() {
        const {visible} = this.props;
        console.log('DeleteJobModalContainer :: visible', visible);
        const {handleCancel, handleConfirm} = this;
        return (
            <DeleteJobModal
                visible={visible}
                onCancel={handleCancel}
                onConfirm={handleConfirm}
            />
        );
    }
}

export default connect(
    (state) => ({
        visible: state.base.getIn(['modal', 'deleteJob']),
        jobName: state.base.getIn(['deleteJobModal', 'jobName']),
        groupName: state.base.getIn(['deleteJobModal', 'groupName'])
    }),
    (dispatch) => ({
        BaseActions: bindActionCreators(baseActions, dispatch),
        JobActions: bindActionCreators(jobActions, dispatch),
    })
)(withRouter(DeleteJobModalContainer));
