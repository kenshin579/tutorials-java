import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as baseActions from 'store/modules/base';
import * as jobActions from "store/modules/job";
import AddJobModal from "components/modal/AddJobModal";
import {withRouter} from "react-router-dom";
import {DELAY_TIME_FOR_MESSAGE} from "../../constants";


class AddJobModalContainer extends Component {
    handleCancel = () => {
        const {BaseActions} = this.props;
        BaseActions.hideModal('addJob');
    };

    showNotification = () => {
        const {BaseActions} = this.props;
        BaseActions.showNotification();
        window.setTimeout(() => {
            BaseActions.hideNotification();
            BaseActions.updateNotificationMessage({message: ''});
        }, DELAY_TIME_FOR_MESSAGE);
    };

    handleSubmit = async (e) => {
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);
        const jobName = formData.get('jobName');
        const groupName = formData.get('groupName');

        const {BaseActions, JobActions, history} = this.props;

        try {
            await JobActions.addJob(formData);
            BaseActions.updateNotificationMessage({message: `${jobName}-${groupName} : 추가 성공하였습니다.`});
        } catch (error) {
            let responseMsg = JSON.parse(error.request.response);
            BaseActions.updateNotificationMessage({message: `${jobName}-${groupName} - ${responseMsg.message} : 추가 실패하였습니다.`});
            console.error('error occurred while adding the job - ', responseMsg.message, error);
        }
        history.push('/');
        BaseActions.hideModal('addJob');
        this.showNotification();
    };

    render() {
        const {visible} = this.props;
        const {handleCancel, handleSubmit} = this;

        return (
            <AddJobModal
                visible={visible}
                onCancel={handleCancel}
                onSubmit={handleSubmit}
            />
        );
    }
}

export default connect(
    (state) => ({
        visible: state.base.getIn(['modal', 'addJob'])
    }),
    (dispatch) => ({
        BaseActions: bindActionCreators(baseActions, dispatch),
        JobActions: bindActionCreators(jobActions, dispatch)
    })
)(withRouter(AddJobModalContainer));
