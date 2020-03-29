import React from 'react';
import PageTemplate from "components/common/PageTemplate";
import ScheduleContainer from "../containers/schedule/ScheduleContainer";

const ListPage = () => {
    return (
        <PageTemplate>
            <ScheduleContainer/>
        </PageTemplate>
    );
};

export default ListPage;
