import React from 'react';
import {Alert} from "react-bootstrap";

const JobAlert = ({visibleNotification, message, onCloseClick}) => {
    return (
        <div>
            {visibleNotification && <Alert variant="primary" onClose={onCloseClick} dismissible>{message}</Alert>}
        </div>
    )
};

export default JobAlert;
