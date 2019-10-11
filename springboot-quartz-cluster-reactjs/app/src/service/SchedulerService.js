import axios from 'axios'
import {API_BASE_URL, ACCESS_TOKEN} from '../constants'

class SchedulerService {

    getAllJobs() {
        console.log('get all jobs');
        let headerConfig = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem(ACCESS_TOKEN)
            }
        };

        // console.log("ACCESS_TOKEN: ", localStorage.getItem(ACCESS_TOKEN));

        return axios.get(`${API_BASE_URL}/jobs`);
    }

    addJob(mediaNo, jobType) {
        return axios.post(`${API_BASE_URL}/media/${mediaNo}/types/${jobType}`);
    }

    deleteJob(jobName) {
        const splits = jobName.split('_');
        const mediaNo = splits[0];
        const jobType = splits.slice(1, splits.length).join('-');
        return axios.delete(`${API_BASE_URL}/media/${mediaNo}/types/${jobType}`);
    }

    getStatus() {
        return axios.get(`${API_BASE_URL}/status`);
    }
}

export default new SchedulerService()