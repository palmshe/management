package com.genius.access.mail.util;

import javax.mail.MessagingException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import com.genius.access.mail.entity.MailParams;

/**
 * @Description:邮件发送器
 * @author xiong.song
 * @date 2016年7月15日 上午9:47:32
 */
public class MailSender {

    private static Logger logger = Logger.getLogger(MailSender.class);
    private static final String HOST_NAME = "smtp.exmail.qq.com";
    private static final String AUTH_NAME = "xiong.song@genius.com.cn";
    private static final String AUTH_PWD = "jL$716034";

    /**
     * @Description：发送邮件
     * @param params
     * @throws EmailException
     * @throws MessagingException 
     */
    public static void send(MailParams params) throws EmailException, MessagingException {
        String routeKey= Integer.toString(params.getAppId());
        Email email = new SimpleEmail();
        email.addHeader("Content-Type", "text/plain; charset=UTF-8");
        email.setHostName(HOST_NAME);
        email.setAuthenticator(new DefaultAuthenticator(AUTH_NAME, AUTH_PWD));
        // email.setSSLOnConnect(true);
        // email.setSslSmtpPort("465");
        email.setFrom(AUTH_NAME);
        email.setSubject(params.getMailTitle());
        logger.info("=====邮件内容====="+params.getMailMessage()+"================");
        email.setMsg(params.getMailMessage());
       // email.getMimeMessage().setText(params.getMailMessage(), "UTF-8");
        setReceivers(email, routeKey, params.getTo(), params.getCc(), params.getBcc());
        String sendString = email.send();
        logger.info("邮件已发送，【routeKey="+routeKey+"】，【sendString="+sendString+"】");
    }
    
    /**
     * @Description：设置收件人
     * @param email
     * @param to
     * @param cc
     * @param bcc
     * @throws EmailException
     */
    
    private static void setReceivers(Email email, String routeKey, String to, String cc,
               String bcc) throws EmailException {
        
        if (to!=null&&!"".equals(to)) {
            String[] toarray= to.split(",");
            for (int i = 0; i < toarray.length; i++) {
                email.addTo(toarray[i]);
            }
        }
        if (cc!=null&&!"".equals(cc)) {
            String[] ccarray= cc.split(",");
            for (int i = 0; i < ccarray.length; i++) {
                email.addTo(ccarray[i]);
            }
        }
        if (bcc!=null&&!"".equals(bcc)) {
            String[] bccarray= bcc.split(",");
            for (int i = 0; i < bccarray.length; i++) {
                email.addTo(bccarray[i]);
            }
        }
        logger.info("收件人设置完成，【routeKey="+routeKey+"】");
    }
}
