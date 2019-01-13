package com.yuanju.productserver.utils;

import com.yuanju.productserver.vo.ResultVO;
/**
 * author: LinjianXiong
 * Date: 2019/1/12
 * Time: 1:06
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(200);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
