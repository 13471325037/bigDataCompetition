package com.sgmw.bigDataCompetition.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*自定义异常实体*/
@Setter
@Getter
@ToString
public class SelfException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String msg;
    int code;
     
     public SelfException(String msg) {
         this.msg = msg;
         this.code = 20000;   //默认code
    }
     
     public SelfException(String msg, int code) {
         this.msg = msg;
         this.code=code;
    }
}