package com.genius.access.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.access.constants.ResultJsonConstants;
import com.genius.access.mail.entity.MailParams;
import com.genius.access.mail.entity.ParamsSource;
import com.genius.access.mail.entity.ResultJson;
import com.genius.access.mail.util.MailSender;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @Description:发送邮件接口
 * @author xiong.song
 * @date 2016年7月13日 上午9:37:41 
 */
@Controller
@RequestMapping("/mailAccess")
public class MailAccessController {

    private static Gson gson= new Gson();
    private static Logger logger= Logger.getLogger(MailAccessController.class);
    
    
    @RequestMapping("/transformRequest")
    @ResponseBody
    public ResultJson transformRequest(HttpServletRequest request, HttpServletResponse response){
        //接口返回
        ResultJson resultJson= new ResultJson();
        try {
            //转换请求参数
            String source= request.getParameter("paramsSource");
            String serialNumber= request.getParameter("serialNumber");
            ParamsSource params=  gson.fromJson(source, ParamsSource.class);
            if(params!=null){
                MailParams mailParams= params.getParams();
                MailSender.send(mailParams);
            }else{
                logger.error("JSON转换实体为NULL，请检查");
                resultJson.setCode(ResultJsonConstants.CODE_NULL);
                resultJson.setMsg(ResultJsonConstants.MSG_NULL);
            }
        } catch (JsonSyntaxException e) {
            logger.error("JSON语法错误", e);
            resultJson.setCode(ResultJsonConstants.CODE_SYNTAX);
            resultJson.setMsg(ResultJsonConstants.MSG_SYNTAX);
        } catch (EmailException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultJson;
    }
    
}
