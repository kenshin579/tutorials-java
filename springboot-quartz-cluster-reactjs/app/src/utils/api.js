import axios from 'axios';

export const addScheduleApi = ({body}) => axios.post(`/api/scheduler/job`, {body});
export const deleteSchedule = ({jobName, groupName}) => axios.delete(`/api/scheduler/job`, {jobName, groupName});
export const getScheduleInfo = () => axios.get(`/api/scheduler/jobs`);