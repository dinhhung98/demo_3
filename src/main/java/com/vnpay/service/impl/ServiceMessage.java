package com.vnpay.service.impl;

import com.vnpay.model.MessageResponse;
import com.vnpay.service.ServiceSMS;
import com.vnpay.util.HandleSMS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ServiceMessage implements ServiceSMS {
    @Value("${vnpay.server.url}")
    String url;
    private static Logger logger = LogManager.getLogger(ServiceMessage.class);
    public List<MessageResponse> getData() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<List<MessageResponse> > futureCall = executor.submit(new HandleSMS());
        List<MessageResponse> result = futureCall.get();
        logger.info("Response {}",result);
        return result;
    }
}
