import axios from 'axios'
import {API_BASE_URL} from '../constants'

class SchedulerService {

    getAllJobs() {
        return axios.get(`${API_BASE_URL}/jobs`);
    }

    // addJob(mediaNo, jobType) {
    //     return axios.post(`${API_BASE_URL}/media/${mediaNo}/types/${jobType}`);
    // }
    //
    // deleteJob(jobName) {
    //     const splits = jobName.split('_');
    //     const mediaNo = splits[0];
    //     const jobType = splits.slice(1, splits.length).join('-');
    //     return axios.delete(`${API_BASE_URL}/media/${mediaNo}/types/${jobType}`);
    // }

    // getStatus() {
    //     return axios.get(`${API_BASE_URL}/jobs`);
    // }
}

export default new SchedulerService()