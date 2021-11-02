package com.vnpay.config;

import com.vnpay.constance.RateConfig;
import com.vnpay.util.LeakyBucket;
import com.vnpay.util.RateLimiter;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ConfigPool {
    public static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
    public static RateLimiter rateLimiter = new LeakyBucket(RateConfig.MAX_REQUEST_PER_SECOND);
}
