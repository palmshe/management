package com.palmshe.mail.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import java.util.Set;

import com.palmshe.mail.constants.MailCounterConstants;

/**
 * @Description: 邮件计数器
 * @author xiong.song
 * @date 2016年7月13日 下午2:41:07 
 */
public class MailsCounter {
    
    private static Logger logger= Logger.getLogger(MailsCounter.class);
    private static Map<String, ArrayList<Long>> mailCounter= new HashMap<String, ArrayList<Long>>(); 
    
    /**
     * @Description：更改对应计数
     * @param counterKey
     * @param count
     */
    public static void changeCount(String counterKey, long count){
        ArrayList<Long> countMap= mailCounter.get(counterKey);
        countMap.set(0, count);
        countMap.set(1, new Date().getTime());
    }
    
    /**
     * @Description：清空
     */
    public static void clearCount(){
        long nowTime= new Date().getTime();
        Set<Entry<String, ArrayList<Long>>> entryMap= mailCounter.entrySet();
        if(entryMap!=null && !entryMap.isEmpty()){
            for (Entry<String, ArrayList<Long>> entry : entryMap) {
                long preTime= entry.getValue().get(1);
                if (nowTime-preTime>MailCounterConstants.TIME_INTERVAL) {
                    entry.getValue().set(0, 0L);
                }
            }
            logger.info("清除计数成功");
        }
    }
    
    /**
     * @Description：判断是否含有key值
     */
    public static boolean hasCounterKey(String counterKey){
        if (mailCounter.containsKey(counterKey)) {
            return true;
        }
        return false;
    }
    
    /**
     * @Description：获取数目
     * @param counterKey
     * @return
     */
    public static long getCount(String counterKey){
        return mailCounter.get(counterKey).get(0).longValue();
    }
    
    /**
     * @Description：获取时间
     * @param counterKey
     * @return
     */
    public static long getTime(String counterKey){
        return mailCounter.get(counterKey).get(1);
    }
}
