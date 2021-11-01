package com.vnpay.config;

public abstract class RateLimiter {

    protected final int maxRequestPerSec;

    protected RateLimiter(int maxRequestPerSec) {
        this.maxRequestPerSec = maxRequestPerSec;
    }

    public abstract boolean checkAllowRequest();
}