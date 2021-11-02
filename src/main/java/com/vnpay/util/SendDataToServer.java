package com.vnpay.util;

import com.vnpay.model.Message;
import com.vnpay.model.MessageResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SendDataToServer implements Runnable {
    private static Logger logger = LogManager.getLogger(SendDataToServer.class);

    private Message message;

    private String url;

    public SendDataToServer(Message message, String url) {
        this.message = message;
        this.url = url;
    }

    private static MessageResponse sendToServer(Message message, String url) {

        try {
            RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, bankRequest, Object.class);
            HttpEntity<Message> requestBody = new HttpEntity<>(message);
            ResponseEntity<MessageResponse> responseEntity = restTemplate.postForEntity(url, requestBody, MessageResponse.class);
            MessageResponse response = responseEntity.getBody();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Send to server: ", e);
            return null;
        }
    }

    @Override
    public void run() {
        logger.info("Result of message: {} and url: {} is response: {}", message, url, sendToServer(message, url));
    }
}
