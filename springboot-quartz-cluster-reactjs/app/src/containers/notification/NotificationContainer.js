import React, {Component} from 'react';
import {connect} from 'react-redux';

class NotificationContainer extends Component {
    render() {
        return (
            <div>
                notification
            </div>
        );
    }
}

export default connect(
    null, null
)(NotificationContainer);
