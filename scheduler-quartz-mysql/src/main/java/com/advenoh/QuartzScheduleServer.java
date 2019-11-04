package kr.pe.advenoh;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class QuartzScheduleServer {
	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			//RMI configuration to make the client to connect to the Quartz server
			prop.put("org.quartz.scheduler.rmi.export", "true");
			prop.put("org.quartz.scheduler.rmi.createRegistry", "true");
			prop.put("org.quartz.scheduler.rmi.registryHost", "localhost");
			prop.put("org.quartz.scheduler.rmi.registryPort", "1098");
			prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
			prop.put("org.quartz.threadPool.threadCount", "5");
			//Quartz Server Properties
			prop.put("quartz.scheduler.instanceName", "ServerScheduler");
//			prop.put("org.quartz.scheduler.instanceId", "AUTO");
			prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
			prop.put("org.quartz.scheduler.instanceId", "NON_CLUSTERED");
			prop.put("org.quartz.scheduler.jobFactory.class", "org.quartz.simpl.SimpleJobFactory");
			prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
			prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
			prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
			prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
			prop.put("org.quartz.jobStore.isClustered", "true");
			//MYSQL DATABASE CONFIGURATION
			//If we do not specify this configuration, QUARTZ will use RAM (in - memory) to store jobs
			//Once we restart QUARTZ, the jobs will not be persisted
			// Configure your MySQL properties
			prop.put("org.quartz.dataSource.quartzDataSource.driver", "com.mysql.jdbc.Driver");
			prop.put("org.quartz.dataSource.quartzDataSource.URL", "jdbc:mysql://localhost:3306/spring_quartz");
			prop.put("org.quartz.dataSource.quartzDataSource.user", "mybatis");
			prop.put("org.quartz.dataSource.quartzDataSource.password", "mybatis$");
			prop.put("org.quartz.dataSource.quartzDataSource.maxConnections", "4");
			SchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(prop);
			Scheduler scheduler = stdSchedulerFactory.getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
