import React from 'react';
import PageTemplate from "../components/common/PageTemplate";
import StatusList from "../components/schedule/StatusList/StatusList";
import ScheduleList from "../components/schedule/ScheduleList";

const ListPage = () => {

    return (
        <PageTemplate>
            <StatusList/>
            <ScheduleList/>
        </PageTemplate>
    );
};

export default ListPage;
