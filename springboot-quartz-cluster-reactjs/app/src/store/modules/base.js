import {createAction, handleActions} from 'redux-actions';
import {Map} from 'immutable';

// action types
const SHOW_MODAL = 'base/SHOW_MODAL';
const HIDE_MODAL = 'base/HIDE_MODAL';
const UPDATE_DELETE_JOB_MODAL = 'base/UPDATE_DELETE_JOB_MODAL';

// action creators
export const showModal = createAction(SHOW_MODAL);
export const hideModal = createAction(HIDE_MODAL);
export const updateDeleteJobModal = createAction(UPDATE_DELETE_JOB_MODAL);

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
}, initialState)
