import axios from 'axios';

export const addJob = ({body}) => axios.post(`/api/scheduler/job`, {body});
export const deleteJob = ({jobName, groupName}) => {
    let params = {jobName: jobName, groupName: groupName};
    axios.delete(`/api/scheduler/job`, {params: params})
};
export const getScheduleInfo = () => axios.get(`/api/scheduler/jobs`);