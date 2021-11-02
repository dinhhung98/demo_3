package com.vnpay.controller;

import com.vnpay.config.ConfigPool;
import com.vnpay.util.RateLimiter;
import com.vnpay.model.MessageResponse;
import com.vnpay.service.impl.ServiceMessage;
import com.vnpay.util.SendDataToServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class SMSController {
    @Autowired
    ServiceMessage serviceMessage;

    private static Logger logger = LogManager.getLogger(SendDataToServer.class);

    private static RateLimiter rateLimiter = ConfigPool.rateLimiter;

    @PostMapping
    public @ResponseBody List<MessageResponse> getSMSMessage(){
        String token = UUID.randomUUID().toString();
        ThreadContext.put("token",token);
        logger.info("Token of request {}",token);
        try {
            List<MessageResponse> result = null;
            if (rateLimiter.checkAllowRequest()){
                logger.info("Begin call api");
                result = serviceMessage.getData();
                logger.info("End call api");
            }
            return result;
        }catch (Exception e){
            logger.error("Internal server error:",e);
            e.printStackTrace();
            return null;
        }
        finally {
            ThreadContext.pop();
            ThreadContext.clearMap();
        }
    }
}
