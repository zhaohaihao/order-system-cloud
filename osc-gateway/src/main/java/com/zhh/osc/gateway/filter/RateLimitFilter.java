package com.zhh.osc.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.zhh.osc.gateway.exception.RateLimitException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author zhh
 * @description
 * @date 2020-04-23 07:42
 */
// @Component
public class RateLimitFilter extends ZuulFilter {

    // 每秒的速率为100
    public static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 这里要比最高的优先级还要高
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimitException();
        }

        return null;
    }
}
