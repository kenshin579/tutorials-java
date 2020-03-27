import {combineReducers} from 'redux';
import list from './list'
import schedule from './schedule'

export default combineReducers({
    list,
    schedule
});