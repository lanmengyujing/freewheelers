package com.trailblazers.freewheelers.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class HtmlMailBuilder {
    private static Logger LOGGER = LoggerFactory.getLogger(HtmlMailBuilder.class);

    private Map<String, Object> model;
    private String toAddress;
    private String subject;
    private String fromAddress;
    private String templateName;
    private Configuration configuration;

    public void buildMailMessage(MimeMessage mimeMessage) throws Exception {
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        mimeMessage.setSubject(subject);
        mimeMessage.setFrom(fromAddress);
        mimeMessage.setContent(getHtmlContent(), "text/html");
    }

    private String getHtmlContent() {
        try {
            Template template = configuration.getTemplate(templateName);
            StringWriter writer = new StringWriter();

            template.process(model, writer);

            return writer.getBuffer().toString();
        } catch (IOException e) {
            LOGGER.error("error when get email content template from freemarker", e);
        } catch (TemplateException e) {
            LOGGER.error("error when get email content template from freemarker", e);
        }
        return "";
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public void setTemplate(String template) {
        this.templateName = template;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
}
