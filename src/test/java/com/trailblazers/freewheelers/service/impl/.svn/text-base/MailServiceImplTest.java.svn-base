package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mail.HtmlMailBuilder;
import com.trailblazers.freewheelers.mail.MailContentRender;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Invoice;
import com.trailblazers.freewheelers.model.PaidOrder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MailServiceImplTest {

    private MailServiceImpl mailService;
    @Mock
    private JavaMailSender mockedJavaMailSender;
    @Mock
    private HtmlMailBuilder mockedHtmlMailBuilder;
    @Mock
    private MailContentRender mockedMailContentRender;

    @Before
    public void setUp() {
        initMocks(this);
        mailService = new MailServiceImpl();
        mailService.setJavaMailSender(mockedJavaMailSender);
    }

    @Test
    public void shouldSendInvoice() throws Exception {
        mailService.send(mockedHtmlMailBuilder, mockedMailContentRender);

        verify(mockedMailContentRender).renderContent(mockedHtmlMailBuilder);
        verify(mockedJavaMailSender).send(any(MimeMessage.class));
    }

}
