import {createAction, handleActions} from 'redux-actions';
import {Map} from 'immutable';
import * as api from "../../utils/api";
import {pender} from "redux-pender";

// action types
const ADD_JOB = 'job/ADD_JOB';
const DELETE_JOB = 'job/DELETE_JOB';

// action creators
export const addJob = createAction(ADD_JOB, api.addJob);
export const deleteJob = createAction(DELETE_JOB, api.deleteJob);

// initial state
const initialState = Map({
    jobName: '',
    groupName: 'DEFAULT',
    cronExpression: '0/20 * * ? * *'
});

// reducer
export default handleActions({
    ...pender({
        type: ADD_JOB,
        onSuccess: (state, action) => {
            const {} = action.payload.data;
            console.log('action.payload1', action.payload);
            // return state.set('postId', _id);
            return;
        }
    })
}, initialState)
