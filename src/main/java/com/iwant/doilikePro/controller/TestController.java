package com.iwant.doilikePro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwant.doilikePro.service.AsyncService;

@RestController
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	
	@Autowired
    private AsyncService asyncService;
	
	 @RequestMapping("/test/{id}")
	    public String firsttest(@PathVariable("id") Integer id) {
		    logger.info("start submit");
		    asyncService.executeAsync();
		    logger.info("end submit");
	        return "firestTest"+id;
	    }
	 
	 @RequestMapping("/test1")
	    public String firsttest1() {
		    logger.info("start submit");
		    asyncService.myexecuteAsync();
		    logger.info("end submit");
	        return "firestTest";
	    }
}
