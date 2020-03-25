import React from 'react';
import styles from './ScheduleList.scss';
import classNames from 'classnames/bind';
import {Button, Card, Table} from "react-bootstrap";

const cx = classNames.bind(styles);

const ScheduleList = () => (
    <div>
        {/*{enableNotification && <Alert variant="primary" onClose={() => {hideNotification()}} dismissible>{message}</Alert>}*/}
        <Card>
            <Card.Header>
                <h6 className={cx('m-0 font-weight-bold text-primary')}>All Schedule Jobs</h6>
            </Card.Header>
            <Card.Body>
                <div className={cx('text-right mb-3')}>
                    <Button variant="primary">
                        새 Job 추가
                    </Button>
                    {/*<SchedulerAddModal*/}
                    {/*    enableModal={enableModal}*/}
                    {/*    onClose={hideModal}*/}
                    {/*    onSubmit={onSubmit}*/}
                    {/*/>*/}
                </div>
                <Table responsive striped hover>
                    <thead className={cx("thread-light")}>
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
                    {/*{scheduleView}*/}
                    </tbody>
                </Table>
            </Card.Body>
        </Card>
    </div>
);

export default ScheduleList;
