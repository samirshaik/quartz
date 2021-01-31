package com.lactusinc.qz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class AppContextListener implements ServletContextListener {
    Scheduler sched = null;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            System.out.println("*****Database connection initialized for Application.");
            SchedulerFactory sf = new StdSchedulerFactory();

            try {
                sched = sf.getScheduler();
            } catch (NoClassDefFoundError e) {
                System.out.println("Error");
                e.printStackTrace();
                return;
            }

            sched.start();
            System.out.println("*****started .");
        } catch (Exception e) {
            System.out.println("35 Error");
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            sched.shutdown(true);
            System.out.println("Database connection closed for Application.");
        } catch (Exception e) {
            System.out.println("35 Error");
            e.printStackTrace();
        }
    }
}