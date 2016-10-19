//package com.genius.access.mail.util;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.genius.access.pojo.MsgStatus;
//import com.genius.access.pojo.SendRecord;
//import com.genius.access.pojo.SendStatus;
//import com.genius.access.service.MsgStatusService;
//import com.genius.access.service.SendRecordService;
//import com.genius.access.service.SendStatusService;
//import com.genius.constants.RabbitmqDispatchConstants;
//import com.genius.mail.entity.MailParams;
//
///**
// * @Description: 邮件计数器
// * @author xiong.song
// * @date 2016年7月13日 下午2:41:07 
// */
//@Component
//public class MailsCounter {
//    
//
//    /**
//     * @Description：添加纪录到数据库
//     * @param sendRecord
//     */
//    public static void addSendRecord(SendRecord sendRecord, SendRecordService sendRecordService) {
//        sendRecordService.addSendRecord(sendRecord);
//    }
//
//    /**
//     * @Description：在规定时间内，判断数据库中是否存在数据
//     * @param sendRecord
//     * @return
//     */
//    public static boolean hasSendRecord(SendRecord sendRecord, SendRecordService sendRecordService, MailParams mailParams, String serialNumber, SendStatusService sendStatusService, MsgStatusService msgStatusService) {
//        
//        List<SendRecord> result= sendRecordService.getSendRecordByContent(sendRecord);
//        if (result!=null && !result.isEmpty()) {
//            //新增邮件状态
//            SendStatus sendStatus= new SendStatus();
//            sendStatus.setAppId(mailParams.getAppId());
//            sendStatus.setSerialNumber(serialNumber);
//            Date tempdate= new Date();
//            sendStatus.setStartTime(tempdate);
//            sendStatus.setUpdateTime(tempdate);
//            sendStatus.setStatusCode(RabbitmqDispatchConstants.STATUS_CODE03);
//            sendStatus.setStatusMsg(RabbitmqDispatchConstants.STATUS_MSG03);
//            sendStatusService.addSendStatus(sendStatus);
//            MsgStatus msgStatus= new MsgStatus();
//            msgStatus.setMsgguid(Long.parseLong(serialNumber));
//            msgStatus.setEmailwarmflag(RabbitmqDispatchConstants.STATUS_CODE03);
//            msgStatusService.uptMsgStatus(msgStatus);
//            return true;
//        }
//        return false;
//    }
//}
