package com.hilox.order.utils;

import com.hilox.order.vo.ResultVO;

/**
 * Created by Hilox on 2018/12/20 0020.
 */
public class ResultVOUtil {

    // 私有化构造
    private ResultVOUtil() {}

    /**
     * 返回成功
     * @return
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 返回成功数据
     * @param object
     * @return
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    /**
     * 返回失败
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
