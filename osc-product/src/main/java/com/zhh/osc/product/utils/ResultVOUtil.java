package com.zhh.osc.product.utils;

import com.zhh.osc.product.vo.ResultVO;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 15:50
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
