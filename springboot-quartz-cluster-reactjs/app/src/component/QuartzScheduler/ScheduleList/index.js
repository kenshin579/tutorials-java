import React, {Component} from 'react';
import './ScheduleList.css';
import ScheduleItem from "../ScheduleItem";
import {Alert, Button, Card, Table} from "react-bootstrap";
import SchedulerAddModal from "../ScheduleAdd/SchedulerAdd";

class ScheduleList extends Component {
    render() {
        const {schedules, message, enableNotification, enableModal, onDelete, onSubmit, hideModal, showModal, hideNotification, showNotification} = this.props;
        const scheduleView = [];
        
        schedules.map(schedule => {
            scheduleView.push(
                <ScheduleItem
                    key={schedule.jobName}
                    schedule={schedule}
                    onDelete={onDelete}>
                </ScheduleItem>
            );
        });

        return (
            <div>
                {enableNotification && <Alert variant="primary" onClose={() => {hideNotification()}} dismissible>{message}</Alert>}
                <Card>
                    <Card.Header>
                        <h6 className="m-0 font-weight-bold text-primary">All Schedule Jobs</h6>
                    </Card.Header>
                    <Card.Body>
                        <div className="text-right mb-3">
                            <Button variant="primary" onClick={() => showModal()}>
                                새 Job 추가
                            </Button>
                            <SchedulerAddModal
                                enableModal={enableModal}
                                onClose={hideModal}
                                onSubmit={onSubmit}
                            />
                        </div>
                        <Table responsive striped hover>
                            <thead className="thread-light">
                            <tr>
                                <th scope="col">Job 이름</th>
                                <th scope="col">Job 그룹</th>
                                <th scope="col">스케줄된 시간</th>
                                <th scope="col">최근 실행된 시간</th>
                                <th scope="col">다음 실행 시간</th>
                                <th scope="col">Job 액션</th>
                                <th scope="col">Job 상태</th>
                            </tr>
                            </thead>
                            <tbody>
                            {scheduleView}
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>
            </div>
        )
    }
}

export default ScheduleList