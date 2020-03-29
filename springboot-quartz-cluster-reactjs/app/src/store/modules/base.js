import {createAction, handleActions} from 'redux-actions';
import {Map} from 'immutable';

// action types
const SHOW_MODAL = 'base/SHOW_MODAL';
const HIDE_MODAL = 'base/HIDE_MODAL';
const UPDATE_DELETE_JOB_MODAL = 'base/UPDATE_DELETE_JOB_MODAL';
const SHOW_NOTIFICATION = 'base/SHOW_NOTIFICATION';
const HIDE_NOTIFICATION = 'base/HIDE_NOTIFICATION';
const UPDATE_NOTIFICATION_MESSAGE = 'base/UPDATE_NOTIFICATION_MESSAGE';

// action creators
export const showModal = createAction(SHOW_MODAL);
export const hideModal = createAction(HIDE_MODAL);
export const updateDeleteJobModal = createAction(UPDATE_DELETE_JOB_MODAL);
export const showNotification = createAction(SHOW_NOTIFICATION);
export const hideNotification = createAction(HIDE_NOTIFICATION);
export const updateNotificationMessage = createAction(UPDATE_NOTIFICATION_MESSAGE);

// initial state
const initialState = Map({
    modal: Map({
        addJob: false,
        deleteJob: false
    }),
    addJobModal: Map({
        error: false
    }),
    deleteJobModal: Map({
        jobName: '',
        groupName: ''
    }),
    notification: Map({
        message: '',
        enable: false
    })
});

// reducer
export default handleActions({
    [SHOW_MODAL]: (state, action) => {
        const {payload: modalName} = action;
        return state.setIn(['modal', modalName], true);
    },
    [HIDE_MODAL]: (state, action) => {
        const {payload: modalName} = action;
        return state.setIn(['modal', modalName], false);
    },
    [UPDATE_DELETE_JOB_MODAL]: (state, action) => {
        const {jobName, groupName} = action.payload;
        return state.setIn(['deleteJobModal', 'jobName'], jobName)
            .setIn(['deleteJobModal', 'groupName'], groupName);
    },
    [SHOW_NOTIFICATION]: (state, action) => {
        return state.setIn(['notification', 'enable'], true);
    },
    [HIDE_NOTIFICATION]: (state, action) => {
        return state.setIn(['notification', 'enable'], false);
    },
    [UPDATE_NOTIFICATION_MESSAGE]: (state, action) => {
        const {message} = action.payload;
        console.log('action.payload', action.payload);
        return state.setIn(['notification', 'message'], message);
    },
}, initialState)
