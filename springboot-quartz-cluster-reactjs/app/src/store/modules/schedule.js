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
    pending: false,
    error: false,
    data: {}
});

// reducer
export default handleActions({
    [POST_SCHEDULE_PENDING]: (state, action) => {
        return state.set('pending', false)
            .set('error', false);
    },
    [POST_SCHEDULE_SUCCESS]: (state, action) => {
        const {data} = action.payload;
        return state.set('pending', false)
            .set('data', data);
    },
    [POST_SCHEDULE_FAILURE]: (state, action) => {
        return state.set('pending', false)
            .set('error', true);
    },
}, initialState)

export const getSchedule = () => dispatch => {
    //먼저 요청이 시작했다는 것을 알립니다.
    // dispatch(getSchedulePending());
    //
    // //요청을 시작합니다. 여기에서 만든 promise를 return해야 나중에 컴포넌트에서
    // //호출할 때 getPost().then(...)을 할 수 있습니다.
    // return api.getScheduleListApi()
    //     .then(response => {
    //         dispatch(getScheduleSuccess(response));
    //         return response;
    //     }).catch(error => {
    //         dispatch(getScheduleFailure(error));
    //         throw(error);
    //     });
};
