package com.zhh.osc.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhh
 * @description
 * @date 2020-04-22 11:51
 */
@Component
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        // 过滤器类型, 前置/路由/后置/错误
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 数字越小代表优先级越高
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // 需要实现的具体的逻辑
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // 这里从URL参数中获取, 也可以cookie, header里获取
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            // 表示不通过
            // requestContext.setSendZuulResponse(false);
            // 设置http状态码
            // requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

        }
        return null;
    }
}
