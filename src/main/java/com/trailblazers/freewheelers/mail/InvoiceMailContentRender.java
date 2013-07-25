package com.trailblazers.freewheelers.mail;

import com.trailblazers.freewheelers.model.Invoice;

import java.util.HashMap;
import java.util.Map;

public class InvoiceMailContentRender implements MailContentRender {

    private Invoice invoice;

    public InvoiceMailContentRender(final Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public void renderContent(HtmlMailBuilder htmlMailBuilder) {
        htmlMailBuilder.setToAddress(invoice.getAccount().getEmailAddress());
        Map<String, Object> invoiceModel = new HashMap<String, Object>();
        invoiceModel.put("invoice", invoice);
        htmlMailBuilder.setModel(invoiceModel);
    }
}
