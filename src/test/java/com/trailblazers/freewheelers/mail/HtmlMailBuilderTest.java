package com.trailblazers.freewheelers.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class HtmlMailBuilderTest {

    public static final String TO_ADDRESS = "to@mail.com";
    public static final String FROM_ADDRESS = "from@mail.com";
    public static final String SUBJECT = "subject";

    @Mock
    private Configuration configuration;
    @Mock
    private Template template;
    @Mock
    private Map<String, Object> model;
    @Mock
    private MimeMessage mimeMessage;

    private HtmlMailBuilder htmlMailBuilder;
    private String TEMPLATE = "template";

    @Before
    public void setUp() {
        initMocks(this);
        htmlMailBuilder = new HtmlMailBuilder();

        htmlMailBuilder.setTemplate(TEMPLATE);
        htmlMailBuilder.setConfiguration(configuration);
        htmlMailBuilder.setToAddress(TO_ADDRESS);
        htmlMailBuilder.setFromAddress(FROM_ADDRESS);
        htmlMailBuilder.setSubject(SUBJECT);
        htmlMailBuilder.setModel(model);
    }

    @Test
    public void shouldBuildMail() throws Exception {
        when(configuration.getTemplate(TEMPLATE)).thenReturn(template);

        doNothing().when(template).process(anyMap(), any(StringWriter.class));

        htmlMailBuilder.buildMailMessage(mimeMessage);

        verify(mimeMessage).addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(TO_ADDRESS));
        verify(mimeMessage).setSubject(anyString());
        verify(mimeMessage).setFrom(anyString());
        verify(mimeMessage).setContent(anyString(), anyString());
    }

}
