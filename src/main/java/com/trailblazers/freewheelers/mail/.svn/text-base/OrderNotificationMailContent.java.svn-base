package com.trailblazers.freewheelers.mail;

import com.trailblazers.freewheelers.model.PaidOrder;

import java.util.HashMap;
import java.util.Map;

public class OrderNotificationMailContent implements MailContentRender {

    private PaidOrder order;

    public OrderNotificationMailContent(final PaidOrder paidOrder) {
        this.order = paidOrder;
    }

    @Override
    public void renderContent(HtmlMailBuilder htmlMailBuilder) {
        htmlMailBuilder.setToAddress(order.getOrders().get(0).getAccount().getEmailAddress());
        Map<String, Object> orderModel = new HashMap<String, Object>();
        orderModel.put("order", order);
        htmlMailBuilder.setModel(orderModel);
    }
}
