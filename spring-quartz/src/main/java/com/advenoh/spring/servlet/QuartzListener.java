//package com.advenoh.spring.servlet;
//
//import com.advenoh.job.TestJob;
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.ee.servlet.QuartzInitializerListener;
//import org.quartz.impl.StdSchedulerFactory;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.annotation.WebListener;
//
///**
// * todo : tomcat 시작할 때 jobs을 생성해서 하고 싶지만, 생각처럼 잘 안됨. DataSource가 null로 인식됨. AutowiringSpringBeanJobFactory에서 세팅을 해주지만
// */
//@Slf4j
//@WebListener
//public class QuartzListener extends QuartzInitializerListener {
//	private StdSchedulerFactory factory = null;
//
//	@Override
//	public void contextInitialized(ServletContextEvent sce) {
//		log.info("[FRANK] contextInitialized");
//		super.contextInitialized(sce);
//
//		ServletContext ctx = sce.getServletContext();
//		StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
//
//		try {
//			Scheduler scheduler = factory.getScheduler();
//			log.info("starting...scheduler");
//			scheduler.start();
//
//			JobDetail jobDetail = JobBuilder
//					.newJob(TestJob.class)
//					.withIdentity("simple1234", "group1").build();
//			Trigger trigger = TriggerBuilder
//					.newTrigger()
//					.withIdentity("simple1234", "group1")
//					.forJob(jobDetail)
//					.withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
//					.startNow().build();
//
//			//			scheduler.scheduleJob(jobDetail, trigger);
//			//			Date date = scheduler.rescheduleJob(new TriggerKey("simple"), trigger);
//			//			log.info("date!!! {}", date);
//
//			//			factory = new StdSchedulerFactory();
//			//			ctx.setAttribute(QUARTZ_FACTORY_KEY, factory);
//			//			Scheduler scheduler = factory.getScheduler();
//			//			scheduler.start();
//
//			scheduler.scheduleJob(jobDetail, trigger);
//		} catch (Exception e) {
//			ctx.log("There was an error scheduling the job.", e);
//		}
//	}
//
//	public void contextDestroyed(ServletContextEvent event) {
//		log.info("[FRANK] contextDestroyed");
//
//		try {
//			factory.getDefaultScheduler().shutdown();
//		} catch (SchedulerException ex) {
//			log.info("catch");
//		}
//	}
//}