import React, {Component} from 'react';
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

class SchedulerAddModal extends Component {
    render() {
        const {showModal, onClose, onSubmit} = this.props;

        return (
            <Modal show={showModal} onHide={() => onClose()}>
                <Modal.Header closeButton>
                    <Modal.Title>새 Job 추가</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate onSubmit={onSubmit}>
                        <Form.Group controlId="formMediaNo">
                            <Form.Label>미디어 번호</Form.Label>
                            <Form.Control type="text" name="mediaNo" placeholder="미디어번호를 입력하세요"/>
                        </Form.Group>

                        <Form.Group controlId="formJobType">
                            <Form.Label>스케줄 Job 타입</Form.Label>
                            <Form.Control name="jobType" as="select">
                                <option></option>
                                <option>LIVE-ID3</option>
                                <option>LIVE-METACOUNTER</option>
                                <option>LIVE-SENDBIRD-STATS</option>
                                <option>LIVE-DEAL-PURCHASE</option>
                            </Form.Control>
                            <Form.Text className="text-muted">
                                추가할 스케줄 Job 타입을 선택주세요
                            </Form.Text>
                        </Form.Group>
                        <div className="text-right">
                            <Button variant="secondary" onClick={() => onClose()}>
                                취소
                            </Button>
                            <Button variant="primary" type="submit">
                                저장
                            </Button>
                        </div>
                    </Form>
                </Modal.Body>
            </Modal>
        )
    }
}

export default SchedulerAddModal