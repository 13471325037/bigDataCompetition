package com.sgmw.bigDataCompetition.exception;

/**
 * @author weitongming
 * @Classname ExceptionEnum
 * @Description 异常枚举
 * @Date 2020/1/10 9:55
 * @Version V1.0
 */
public enum ExceptionEnum implements BaseException {




    /*
     * 导出相关异常
     */
    INEXISTENCE_FILE(30001,"文件不存在"),
    DERIVE_PERMISSION_DENIED(30002,"无法下载该文件"),
    DERIVE_UNKNOWN_EXCEPTION(30003,"下载文件异常"),
    REPEAT_TEAM_NAME(30004, "队名已存在，请重新取名!")
    ;



    private int code ;

    private String message ;


    ExceptionEnum(int code ,String message){
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
