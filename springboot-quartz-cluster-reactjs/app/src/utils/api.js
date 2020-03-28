import axios from 'axios';

export const addScheduleApi = ({body}) => axios.post(`/api/scheduler/job`, {body});
export const getScheduleInfoApi = () => axios.get(`/api/scheduler/jobs`);