package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mail.HtmlMailBuilder;
import com.trailblazers.freewheelers.mail.MailContentRender;
import com.trailblazers.freewheelers.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import javax.mail.internet.MimeMessage;

public class MailServiceImpl implements MailService {

    private JavaMailSender javaMailSender;
    private Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Async
    @Override
    public void send(HtmlMailBuilder htmlMailBuilder, MailContentRender mailContentRender) {
        mailContentRender.renderContent(htmlMailBuilder);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            htmlMailBuilder.buildMailMessage(mimeMessage);
            javaMailSender.send(mimeMessage);
            log.info("Mail sent");
        } catch (Exception e) {
            log.error("Mail sending fails", e);
        }
    }


    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
