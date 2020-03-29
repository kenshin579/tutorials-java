import React from 'react';
import styles from './AddJobModal.scss';
import classNames from 'classnames/bind';
import {Button, Form, Modal} from "react-bootstrap";

const cx = classNames.bind(styles);

const AddJobModal = ({visible, onCancel, onSubmit}) => (
    <Modal show={visible} onHide={onCancel}>
        <Modal.Header closeButton>
            <Modal.Title>새 Job 추가</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <Form noValidate onSubmit={onSubmit}>
                <Form.Group controlId="formJobName">
                    <Form.Label>Job 이름</Form.Label>
                    <Form.Control type="text" name="jobName" placeholder="Job 이름을 입력하세요"/>
                </Form.Group>
                <Form.Group controlId="formGroupName">
                    <Form.Label>그룹 이름</Form.Label>
                    <Form.Control type="text" name="groupName" placeholder="그룹 이름을 입력하세요"/>
                </Form.Group>
                <Form.Group controlId="formCronExpression">
                    <Form.Label>Cron 표현식</Form.Label>
                    <Form.Control type="text" name="cronExpression" placeholder="Cron 표현식을 입력하세요"/>
                </Form.Group>
                {/*<Form.Group controlId="formStartDateAt">*/}
                {/*    <Form.Label>시작 날짜</Form.Label>*/}
                {/*    <Form.Control type="text" name="startDateAt" placeholder="시작 날짜를 입력하세요"/>*/}
                {/*</Form.Group>*/}
                {/*<Form.Group controlId="formRepeatIntervalInSeconds">*/}
                {/*    <Form.Label>반복 인터벌</Form.Label>*/}
                {/*    <Form.Control type="text" name="repeatIntervalInSeconds" placeholder="반복 인터벌(초)를 입력하세요"/>*/}
                {/*</Form.Group>*/}
                {/*<Form.Group controlId="formRepeatCount">*/}
                {/*    <Form.Label>반복 횟수</Form.Label>*/}
                {/*    <Form.Control type="text" name="repeatCount" placeholder="반복 횟수를 입력하세요"/>*/}
                {/*</Form.Group>*/}

                {/*<Form.Group controlId="formJobType">*/}
                {/*    <Form.Label>스케줄 Job 클래스</Form.Label>*/}
                {/*    <Form.Control name="jobType" as="select">*/}
                {/*        <option></option>*/}
                {/*        <option>CronJob2</option>*/}
                {/*        <option>SimpleJob</option>*/}
                {/*    </Form.Control>*/}
                {/*    <Form.Text className="text-muted">*/}
                {/*        추가할 스케줄 Job 클래스을 선택주세요*/}
                {/*    </Form.Text>*/}
                {/*</Form.Group>*/}
                <div className="text-right">
                    <Button variant="secondary" onClick={onCancel}>
                        취소
                    </Button>
                    <Button variant="primary" type="submit">
                        저장
                    </Button>
                </div>
            </Form>
        </Modal.Body>
    </Modal>
);

export default AddJobModal;
