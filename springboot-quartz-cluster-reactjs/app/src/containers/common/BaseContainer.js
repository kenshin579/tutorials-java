import React, {Component} from 'react';
import {connect} from 'react-redux';
import DeleteJobModalContainer from "../modal/DeleteJobModalContainer";

class BaseContainer extends Component {
    render() {
        return (
            <div>
                <DeleteJobModalContainer/>
                { /* 전역적으로 사용되는 컴포넌트들이 있다면 여기서 렌더링합니다. */}
            </div>
        )
    }
}

export default connect(
    null,
    null
)(BaseContainer);
