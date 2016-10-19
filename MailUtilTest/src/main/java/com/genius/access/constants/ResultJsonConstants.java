package com.genius.access.constants;

/**
 * @Description:接口返回常量
 * @author xiong.song
 * @date 2016年7月13日 下午2:23:55 
 */
public class ResultJsonConstants {
    
    public static final int CODE_OUT= 1001;
    public static final int CODE_SUCCESS= 1002;
    public static final int CODE_SYNTAX = 1003;
    public static final int CODE_NULL = 1004;
    public static final int CODE_EXSIT= 1005;
    public static final String MSG_OUT="规定时间内，邮件投递过多";
    public static final String MSG_SUCCESS="邮件已存到队列，等待发送";
    public static final String MSG_SYNTAX="JSON语法错误，请检查";
    public static final String MSG_NULL = "JSON转换实体为NULL";
    public static final String MSG_EXSIT="规定时间内，邮件已投递";
    
}
