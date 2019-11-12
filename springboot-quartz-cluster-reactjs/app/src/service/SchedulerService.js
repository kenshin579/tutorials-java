import axios from 'axios'
import {API_SCHEDULE_BASE_URL} from '../constants'

class SchedulerService {

    getAllJobs() {
        return axios.get(`${API_SCHEDULE_BASE_URL}/jobs`);
    }

    addJob(jobName, groupName, cronExpression, startDateAt, repeatIntervalInSeconds, repeatCount) {
        console.log(jobName, groupName, cronExpression, startDateAt, repeatIntervalInSeconds, repeatCount);
        let requestBody = {
            jobName: jobName, groupName: groupName,
            cronExpression: cronExpression, startDateAt: startDateAt,
            repeatIntervalInSeconds: repeatIntervalInSeconds, repeatCount: repeatCount
        };
        let config = {
            headers: {'Content-type': 'application/x-www-form-urlencoded'}
        };
        console.log('requestBody', requestBody);
        return axios.post(`${API_SCHEDULE_BASE_URL}/job`, requestBody, config);
    }

    deleteJob(jobName, groupName) {
        console.log('jobName', jobName, 'groupName', groupName);
        let params = {jobName: jobName, groupName: groupName};
        return axios.delete(`${API_SCHEDULE_BASE_URL}/job`, {params: params});
    }
}

export default new SchedulerService()