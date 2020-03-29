import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as baseActions from 'store/modules/base';

class AddJobModalContainer extends Component {
    handleCancel = () => {
        const {BaseActions} = this.props;
        BaseActions.hideModal('addJob');
    };

    handleConfirm = async () => {
        const {BaseActions} = this.props;
        const {id} = match.params;

        try {
            await PostActions.removePost(id);
            BaseActions.hideModal('addJob');
        } catch (e) {
            console.log(e);
        }
    };

    render() {
        const {handleDeleteJob, handleAddJob} = this;

        return (
            <div>
                add
            </div>
        );
    }
}

export default connect(
    (state) => ({
        visible: state.base.getIn(['modal', 'addJob'])
    }),
    (dispatch) => ({
        BaseActions: bindActionCreators(baseActions, dispatch),
    })
)(AddJobModalContainer);
