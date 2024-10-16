package com.yizhi.oj.ex;

/**
 * @Author: Zing
 * @Date: 2023/03/19/21:18
 * @Description: 自定义业务异常
 */
public class CustomException extends RuntimeException{

    public CustomException(String msg){
        super(msg);
    }

}
