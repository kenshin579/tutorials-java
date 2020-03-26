import React from 'react';
import PageTemplate from "components/common/PageTemplate";
import StatusListContainer from "../containers/status/StatusListContainer";

const ListPage = () => {

    return (
        <PageTemplate>
            {/*<StatusList/>*/}
            {/*<ScheduleList/>*/}
            <StatusListContainer/>
        </PageTemplate>
    );
};

export default ListPage;
