import {createAction, handleActions} from 'redux-actions';
import {Map} from 'immutable';

// action types
const POST_SCHEDULE_PENDING = 'schedule/POST_SCHEDULE_PENDING';
const POST_SCHEDULE_SUCCESS = 'schedule/POST_SCHEDULE_SUCCESS';
const POST_SCHEDULE_FAILURE = 'schedule/POST_SCHEDULE_FAILURE';

// action creators
export const postSchedulePending = createAction(POST_SCHEDULE_PENDING);
export const postcheduleSuccess = createAction(POST_SCHEDULE_SUCCESS);
export const postScheduleFailure = createAction(POST_SCHEDULE_FAILURE);

// initial state
const initialState = Map({
    data: {}
});

// reducer
export default handleActions({
    // [POST_SCHEDULE_PENDING]: (state, action) => {
    //     return state.set('pending', false)
    //         .set('error', false);
    // },
    // [POST_SCHEDULE_SUCCESS]: (state, action) => {
    //     const {data} = action.payload;
    //     return state.set('pending', false)
    //         .set('data', data);
    // },
    // [POST_SCHEDULE_FAILURE]: (state, action) => {
    //     return state.set('pending', false)
    //         .set('error', true);
    // },
}, initialState)
