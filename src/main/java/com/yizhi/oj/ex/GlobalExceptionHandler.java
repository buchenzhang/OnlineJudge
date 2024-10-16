package com.yizhi.oj.ex;

import com.yizhi.oj.common.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zing
 * @Date: 2023/03/19/21:20
 * @Description: 全局异常处理器
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * @Description: 异常处理方法
     * @Param: [ex]
     * @return: com.yihzi.crm.common.R<java.lang.String>
     * @Author: Zing
     */
    @ExceptionHandler(CustomException.class)
    public ResponseResult<String> exceptionHandler(CustomException ex){
        return new ResponseResult(200,ex.getMessage());
    }

}

