package com.hilox.order.handler;

import com.hilox.order.config.ProjectUrlConfig;
import com.hilox.order.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理器
 * Created by Hilox on 2018/12/29 0029.
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 拦截登录异常
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handleAuthorizeException() {
        return new ModelAndView("redirect:"
            .concat(projectUrlConfig.getWechatOpenAuthorizeUrl()
            .concat("/hilox-order/wechat/qrAuthorize")
            .concat("?returnUrl=")
            .concat(projectUrlConfig.getHilox())
            .concat("/hilox-order/seller/login")));
    }
}
