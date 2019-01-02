package com.hilox.order.handler;

import com.hilox.order.config.ProjectUrlConfig;
import com.hilox.order.exception.SellException;
import com.hilox.order.exception.SellerAuthorizeException;
import com.hilox.order.exception.TestException;
import com.hilox.order.utils.ResultVOUtil;
import com.hilox.order.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    /**
     * 拦截处理业务异常<br/>
     * 这种处理方式不会修改http响应头, 响应头返回的状态码还是200
     * @param e
     * @return
     */
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handleSellException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 特殊错误情况处理<br/>
     * 这种处理方式会修改http响应头
     */
    @ExceptionHandler(value = TestException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleTestException() {

    }
}
