use spring_boot_quartz_cluster;


# ==============================================================================
# TEST 스트립트
# ==============================================================================
SELECT * FROM job_history;
SELECT * FROM job_status;

SELECT * FROM job_history WHERE job_name = 'job20';

SELECT * FROM job_history jh
INNER JOIN job_status js WHERE jh.job_name = 'job20'
ORDER BY status_id DESC;