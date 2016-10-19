package com.palmshe.access.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.palmshe.mail.bean.MailsCounter;
import com.palmshe.mail.bean.ParamsSource;
import com.palmshe.mail.bean.ResultJson;
import com.palmshe.mail.constants.ResultJsonConstants;

/**
 * @Description:发送邮件接口
 * @author xiong.song
 * @date 2016年7月13日 上午9:37:41 
 */
@Controller("/mainAccess")
public class MailAccessController {

    private static final Integer counterLimit= 5;
    private static Gson gson= new Gson();
    private static Logger logger= Logger.getLogger(MailAccessController.class);
    
    @RequestMapping("transformRequest")
    public void transformRequest(HttpServletRequest request, HttpServletResponse response){
        //接口返回
        ResultJson resultJson= new ResultJson();
        try {
            //转换请求参数
            String source= request.getParameter("paramsSource");
            ParamsSource params=  gson.fromJson(source, ParamsSource.class);
            if(params!=null){
                String counterKey= params.getParams().getAppId()+"-"+params.getParams().getBusinessId();
                if (MailsCounter.hasCounterKey(counterKey)&& 
                   Long.compare(MailsCounter.getCount(counterKey), counterLimit)>5) 
                {//存在appId-businessId的键值，并且到达累计数目时
                    resultJson.setCode(ResultJsonConstants.CODE_OUT);
                    resultJson.setMsg(ResultJsonConstants.MSG_OUT);
                    resultJson.setCounterKey(counterKey);
                    resultJson.setCount(MailsCounter.getCount(counterKey));
                }else if(MailsCounter.hasCounterKey(counterKey)){
                    //TODO 发送邮件
                   long count= MailsCounter.getCount(counterKey);
                   count++;
                   MailsCounter.changeCount(counterKey, count);
                   resultJson.setCode(ResultJsonConstants.CODE_SUCCESS);
                   resultJson.setMsg(ResultJsonConstants.MSG_SUCCESS);
                   resultJson.setCounterKey(counterKey);
                   resultJson.setCount(MailsCounter.getCount(counterKey));
                }
            }else{
                logger.error("JSON转换实体为NULL，请检查");
                resultJson.setCode(ResultJsonConstants.CODE_NULL);
                resultJson.setMsg(ResultJsonConstants.MSG_NULL);
            }
        } catch (JsonSyntaxException e) {
            logger.error("JSON语法错误", e);
            resultJson.setCode(ResultJsonConstants.CODE_SYNTAX);
            resultJson.setMsg(ResultJsonConstants.MSG_SYNTAX);
        }
    }
    
}
