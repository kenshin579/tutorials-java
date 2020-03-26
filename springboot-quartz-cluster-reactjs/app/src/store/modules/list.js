import {createAction, handleActions} from 'redux-actions';
import {List, Map} from 'immutable';
import * as api from 'utils/api';

// action types
const GET_SCHEDULE_PENDING = 'list/GET_SCHEDULE_PENDING';
const GET_SCHEDULE_SUCCESS = 'list/GET_SCHEDULE_SUCCESS';
const GET_SCHEDULE_FAILURE = 'list/GET_SCHEDULE_FAILURE';

// action creators
export const getSchedulePending = createAction(GET_SCHEDULE_PENDING);
export const getScheduleSuccess = createAction(GET_SCHEDULE_SUCCESS);
export const getScheduleFailure = createAction(GET_SCHEDULE_FAILURE);

// initial state
const initialState = Map({
    pending: false,
    error: false,
    data: {
        numOfAllJobs: 0,
        numOfGroups: 0,
        numOfRunningJobs: 0,
        jobs: List()
    }
});

// reducer
export default handleActions({
    [GET_SCHEDULE_PENDING]: (state, action) => {
        return state.set('pending', false)
            .set('error', false);
    },
    [GET_SCHEDULE_SUCCESS]: (state, action) => {
        const {data} = action.payload;
        return state.set('pending', false)
            .set('data', data);
    },
    [GET_SCHEDULE_FAILURE]: (state, action) => {
        return state.set('pending', false)
            .set('error', true);
    },
}, initialState)

export const getSchedule = () => dispatch => {
    //먼저 요청이 시작했다는 것을 알립니다.
    dispatch(getSchedulePending());

    //요청을 시작합니다. 여기에서 만든 promise를 return해야 나중에 컴포넌트에서
    //호출할 때 getPost().then(...)을 할 수 있습니다.
    return api.getScheduleListApi()
        .then(response => {
            dispatch(getScheduleSuccess(response));
            return response;
        }).catch(error => {
            dispatch(getScheduleFailure(error));
            throw(error);
        });
};
