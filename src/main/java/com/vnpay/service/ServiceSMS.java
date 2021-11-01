package com.vnpay.service;

import com.vnpay.model.MessageResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ServiceSMS {
    public List<MessageResponse> getData() throws ExecutionException, InterruptedException;
}
