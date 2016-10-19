package com.palmshe.access.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.palmshe.mail.bean.MailsCounter;

@Component
public class ClearCounterScheduled {
    
    @Scheduled(cron="0 */5 * * * ? ")
    public void clearCounter() {
        MailsCounter.clearCount();
    }
}
