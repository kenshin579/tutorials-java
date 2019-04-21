import axios from 'axios'
import {SCHEDULER_API_URL} from '../constants'

class SchedulerService {

    getJobs() {
        console.log('get all jobs');
        return axios.get(`${SCHEDULER_API_URL}/jobs`);
    }

    scheduleJob(data) {
        console.log('schedule job', data);
        return axios.post(`${SCHEDULER_API_URL}/###`);
    }

    deleteJob(jobName) {
        console.log('delete schedule job');
        return axios.delete(`${SCHEDULER_API_URL}/${jobName}`);
    }
}

export default new SchedulerService()