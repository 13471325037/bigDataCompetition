package com.sgmw.bigDataCompetition.exception;

/**
 * @author weitongming
 * @Classname BaseException
 * @Description 基础的异常接口
 * @Date 2020/1/10 9:54
 * @Version V1.0
 */
public interface BaseException {

    int getCode();

    String getMessage();
}
