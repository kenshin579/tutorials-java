import axios from 'axios';

export const addJob = (formData) => {
    return axios.post(`/api/scheduler/job`, formData);
};

export const deleteJob = ({jobName, groupName}) => {
    let params = {jobName: jobName, groupName: groupName};
    return axios.delete(`/api/scheduler/job`, {params: params})
};
export const getScheduleInfo = () => axios.get(`/api/scheduler/jobs`);