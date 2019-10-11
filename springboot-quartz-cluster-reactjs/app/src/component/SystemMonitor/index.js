import React, {Component} from 'react'

class SystemMonitor extends Component {
    render() {
        console.log('monitor render');
        return (
            <div className="container">
                <h3>Monitoring page - 작업중</h3>
                <li>서버 모니터링 (dev, qa등)</li>
                <ul>
                    <li>Third-party 서비스 모니터링(redis, memcached, rabbitmq등)</li>
                    <li>Application관련 모니터링(deal관련 체크)</li>
                    <li>Teams Webhook으로 notification 보내기</li>
                </ul>
            </div>
        )
    }
}

export default SystemMonitor