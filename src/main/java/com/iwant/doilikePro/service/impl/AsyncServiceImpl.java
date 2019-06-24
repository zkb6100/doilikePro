package com.iwant.doilikePro.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.iwant.doilikePro.service.AsyncService;
@Service
public class AsyncServiceImpl implements AsyncService{
	private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
	@Override
	@Async("asyncServiceExecutor")
	public void executeAsync() {
		logger.info("start executeAsync");
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end executeAsync");
	}
	
	@Override
	@Async("mySimpleAsync")
	public void myexecuteAsync() {
		logger.info("start mySimpleAsync");
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end mySimpleAsync");
	}

}
