import {combineReducers} from 'redux';
import list from './list'
import job from './job'
import base from './base'
import {penderReducer} from 'redux-pender';

export default combineReducers({
    base,
    list,
    job,
    pender: penderReducer
});