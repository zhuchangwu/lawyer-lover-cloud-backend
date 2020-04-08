package com.changwu.utils;

public class ResultVOUtil {

    // 成功
    public static ResultVO success(Object obj){
        ResultVO objectResultVO = new ResultVO<>();
        objectResultVO.setData(obj);
        objectResultVO.setMsg("成功");
        objectResultVO.setCode(200);
        return objectResultVO;
    }

    // 成功
    public static ResultVO success(){
        return success(null);
    }

    // 失败
    public static ResultVO error(Integer code,String msg){
        ResultVO objectResultVO = new ResultVO<>();
        objectResultVO.setMsg(msg);
        objectResultVO.setCode(code);
        return objectResultVO;
    }


}
