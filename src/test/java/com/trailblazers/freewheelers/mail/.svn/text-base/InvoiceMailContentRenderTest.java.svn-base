package com.trailblazers.freewheelers.mail;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceMailContentRenderTest {
    @Mock
    private HtmlMailBuilder mockedHtmlMailBuilder;
    private InvoiceMailContentRender invoiceMailContentRender;
    private Invoice invoice;

    @Before
    public void setUp() {
        initMocks(this);

        invoice = new Invoice();
        invoice.setAccount(new Account().setEmailAddress("to@add.ress"));

        invoiceMailContentRender = new InvoiceMailContentRender(invoice);
    }

    @Test
    public void shouldSetToAddress() throws Exception {
        invoiceMailContentRender.renderContent(mockedHtmlMailBuilder);

        verify(mockedHtmlMailBuilder).setToAddress("to@add.ress");
    }

    @Test
    public void shouldSetModel() throws Exception {
        invoiceMailContentRender.renderContent(mockedHtmlMailBuilder);

        verify(mockedHtmlMailBuilder).setModel(anyMap());
    }
}
