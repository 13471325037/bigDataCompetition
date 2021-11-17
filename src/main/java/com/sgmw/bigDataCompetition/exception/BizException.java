package com.sgmw.bigDataCompetition.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author weitongming
 * @Classname BizException
 * @Description 业务异常
 * @Date 2020/1/10 14:54
 * @Version V1.0
 */
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BizException extends RuntimeException {


    private int code = 200;

    private String msg;

    private Object data;


    public BizException(ExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMessage();
    }



}
