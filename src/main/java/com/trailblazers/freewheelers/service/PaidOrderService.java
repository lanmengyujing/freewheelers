package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.PaidOrder;

import java.util.List;

public interface PaidOrderService {
    void moveItemsToPaidForAccountId(Long accountId);

    void setItemService(ItemService itemService);

    void setReserveOrderService(ReserveOrderService reserveOrderService);

    List<PaidOrder> getAllPaidOrders();
}
