import React from 'react';
import styles from './DeleteJobModal.scss';
import classNames from 'classnames/bind';
import {Button, Modal} from "react-bootstrap";

const cx = classNames.bind(styles);

const DeleteJobModal = ({visible, onConfirm, onCancel}) => (
    <Modal show={visible} onHide={onCancel}>
        <Modal.Header closeButton>
            <Modal.Title>Job 삭제</Modal.Title>
        </Modal.Header>
        <Modal.Body>이 Job을 정말로 삭제하시겠습니까?</Modal.Body>
        <Modal.Footer>
            <Button variant="secondary" onClick={onCancel}>
                취소
            </Button>
            <Button variant="primary" onClick={onConfirm}>
                삭제
            </Button>
        </Modal.Footer>
    </Modal>
);

export default DeleteJobModal;
