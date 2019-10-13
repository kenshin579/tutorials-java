import React, {Component} from 'react';
import SchedulerService from '../../../service/SchedulerService';
import './ScheduleList.css';
import ScheduleItem from "../ScheduleItem";
import {DELAY_TIME_FOR_MESSAGE} from "../../../constants";
import SchedulerAddModal from "../ScheduleAdd/SchedulerAdd";
import {Alert, Button, Card, Table} from "react-bootstrap";

class ScheduleList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            schedulers: [],
            message: null,
            showModal: false,
            showNotification: false,
            visible: false,
            newSchedule: {}
        };
        this.deleteScheduleClicked = this.deleteScheduleClicked.bind(this);
        this.addScheduleSubmit = this.addScheduleSubmit.bind(this);
        this.refreshSchedules = this.refreshSchedules.bind(this);
        this.hideModal = this.hideModal.bind(this);
        this.showModal = this.showModal.bind(this);
    }

    deleteScheduleClicked(jobName) {
        SchedulerService.deleteJob(jobName)
            .then(response => {
                    this.setState({message: `${jobName}을 성공적으로 삭제하였습니다.`});
                    this.refreshSchedules();
                    this.showNotification();
                }
            ).catch(error => {
                console.error('error occurred while deleting the job', error);
            }
        );
    }

    addScheduleSubmit(e) {
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);
        const mediaNo = formData.get('mediaNo');
        const jobType = formData.get('jobType');
        console.log('mediaNo', mediaNo, 'jobType', jobType);

        SchedulerService.addJob(mediaNo, jobType)
            .then(response => {
                    this.setState({message: `${mediaNo}-${jobType} 성공적으로 추가되었습니다.`});
                    this.refreshSchedules();
                    this.showNotification();
                }
            ).catch(error => {
                console.error('error occurred while adding the job', error);
            }
        );

        this.hideModal();
    }

    hideModal() {
        this.setState({showModal: false});
    }

    showModal() {
        this.setState({showModal: true});
    }

    hideNotification() {
        this.setState({showNotification: false});
    }

    showNotification() {
        this.setState({showNotification: true}, () => {
            window.setTimeout(() => {
                this.setState({showNotification: false})
            }, DELAY_TIME_FOR_MESSAGE);
        });
    }

    render() {
        console.log('render schedule list');
        const scheduleView = [];
        this.state.schedulers.map(schedule => {
            console.log('schedule', schedule);
            scheduleView.push(
                <ScheduleItem
                    key={schedule.jobName}
                    schedule={schedule}
                    onDelete={this.deleteScheduleClicked}>
                </ScheduleItem>
            );
        });

        return (
            <div>
                {this.state.showNotification && <Alert variant="primary" onClose={() => {
                    this.hideNotification()
                }} dismissible>{this.state.message}</Alert>}
                <Card>
                    <Card.Header>
                        <h6 className="m-0 font-weight-bold text-primary">All Schedule Jobs</h6>
                    </Card.Header>
                    <Card.Body>
                        <div className="text-right mb-3">
                            <Button variant="primary" onClick={this.showModal}>
                                새 Job 추가
                            </Button>
                            <SchedulerAddModal
                                showModal={this.state.showModal}
                                onSubmit={this.addScheduleSubmit}
                                onClose={this.hideModal}
                            />
                        </div>
                        <Table responsive striped hover>
                            <thead className="thread-light">
                            <tr>
                                <th scope="col">Job 이름</th>
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