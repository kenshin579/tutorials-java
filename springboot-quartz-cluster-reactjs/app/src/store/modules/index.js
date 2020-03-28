import {combineReducers} from 'redux';
import list from './list'
import schedule from './schedule'
import {penderReducer} from 'redux-pender';

export default combineReducers({
    list,
    schedule,
    pender: penderReducer
});