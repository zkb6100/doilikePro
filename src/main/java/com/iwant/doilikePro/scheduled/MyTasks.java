package com.iwant.doilikePro.scheduled;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTasks {
	
	private static final Logger logger = LoggerFactory.getLogger(MyTasks.class);
	
	//@Scheduled(cron="0/1 * * * * ? ")   //每1秒执行一次
    public void testCron1() {
       DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        logger.info(sdf.format(new Date())+"*********每1秒执行一次");
    }
	//@Scheduled(cron="0/1 * * * * ? ")   //每1秒执行一次 
    public void testCron2() {  
        logger.info("只负责输出每1秒执行一次");
    }

}
