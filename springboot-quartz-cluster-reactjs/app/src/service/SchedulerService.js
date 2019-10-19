import axios from 'axios'
import {API_SCHEDULE_BASE_URL} from '../constants'

class SchedulerService {

    getAllJobs() {
        return axios.get(`${API_SCHEDULE_BASE_URL}/jobs`);
    }

    // addJob(mediaNo, jobType) {
    //     return axios.post(`${API_SCHEDULE_BASE_URL}/media/${mediaNo}/types/${jobType}`);
    // }
    //

    deleteJob(jobName, groupName) {
        console.log('jobName', jobName, 'groupName', groupName);
        let params = {jobName: jobName, groupName: groupName};
        return axios.delete(`${API_SCHEDULE_BASE_URL}/job`, {params: params});
    }
}

export default new SchedulerService()