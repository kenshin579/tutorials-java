import {createAction, handleActions} from 'redux-actions';
import {List, Map} from 'immutable';
import * as api from 'utils/api';
import {pender} from 'redux-pender';

// action types
const GET_SCHEDULE_INFO = 'list/GET_SCHEDULE_INFO';

// action creators
export const getScheduleInfo = createAction(GET_SCHEDULE_INFO, api.getScheduleInfoApi);

// initial state
const initialState = Map({
    data: {
        numOfAllJobs: 0,
        numOfGroups: 0,
        numOfRunningJobs: 0,
        jobs: List()
    }
});

// reducer
export default handleActions({
    ...pender({
        type: GET_SCHEDULE_INFO,
        onSuccess: (state, action) => {
            const {data} = action.payload;
            return state.set('data', data);
        }
    })
}, initialState)