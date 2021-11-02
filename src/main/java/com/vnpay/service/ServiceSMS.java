package com.vnpay.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ServiceSMS {
    public String getData() throws ExecutionException, InterruptedException, IOException;
}
