package com.vnpay.service.impl;

import com.vnpay.config.ConfigPool;
import com.vnpay.model.Message;
import com.vnpay.service.ServiceSMS;
import com.vnpay.util.RateLimiter;
import com.vnpay.util.ReadExcel;
import com.vnpay.util.SendDataToServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@Service
public class ServiceMessage implements ServiceSMS {
    private static Logger logger = LogManager.getLogger(ServiceMessage.class);
    private static RateLimiter rateLimiter = ConfigPool.rateLimiter;

    @Value("${vnpay.server.url}")
    private String url;

    public String getData() throws IOException {
        ThreadPoolExecutor executor = ConfigPool.threadPoolExecutor;
        List<Message> messages = ReadExcel.readData();
        SendDataToServer sendDataToServer;

        for (Message message:messages){
            if (rateLimiter.checkAllowRequest()){
                String messageId = UUID.randomUUID().toString();
                message.setMessageId(messageId);
                sendDataToServer = new SendDataToServer(message,url);
                executor.execute(sendDataToServer);
            }
        }
        Files.move(Paths.get("Processing/demo.xlsx"),Paths.get("Archive/demo.xlsx"), StandardCopyOption.REPLACE_EXISTING);
        executor.shutdown();
        return "done";
    }
}
