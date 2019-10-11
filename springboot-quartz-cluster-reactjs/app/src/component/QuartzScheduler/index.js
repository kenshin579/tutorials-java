import React, {Component} from 'react';
import ScheduleList from './ScheduleList';
import ScheduleStatus from './ScheduleStatus';

class QuartzScheduler extends Component {
    render() {
        /*
        todo : 개선 작업
        - as-as : ScheduleStatus와 ScheduleList 각각 다른 API로 호출함
        - to-be : 여기에서 API (/api/schedulers/jobs)를 한번만 호출하도록 변경작업 필요 (redux 사용하기)
         */
        return (
            <div className="container-fluid">
                <ScheduleStatus/>
                <ScheduleList/>
            </div>
        )
    }
}

export default QuartzScheduler