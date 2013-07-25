package com.trailblazers.freewheelers.mail;

import com.google.common.collect.ImmutableList;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.PaidOrder;
import com.trailblazers.freewheelers.model.ReserveOrder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderNotificationMailContentTest {

    @Mock
    private HtmlMailBuilder mockedHtmlMailBuilder;
    private PaidOrder paidOrder;
    private OrderNotificationMailContent orderNotificationMailContent;

    @Before
    public void setUp() {
        initMocks(this);

        paidOrder = new PaidOrder();
        paidOrder.setOrders(ImmutableList.of(new ReserveOrder().setAccount(new Account().setEmailAddress("to"))));
        orderNotificationMailContent = new OrderNotificationMailContent(paidOrder);
    }

    @Test
    public void shouldSetToAddress() throws Exception {
        orderNotificationMailContent.renderContent(mockedHtmlMailBuilder);
        verify(mockedHtmlMailBuilder).setToAddress("to");
    }

    @Test
    public void shouldSetModel() throws Exception {
        orderNotificationMailContent.renderContent(mockedHtmlMailBuilder);
        verify(mockedHtmlMailBuilder).setModel(anyMap());
    }
}
