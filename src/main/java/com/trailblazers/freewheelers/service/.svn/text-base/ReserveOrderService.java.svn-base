package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Invoice;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;

import java.util.List;

public interface ReserveOrderService {

    void save(ReserveOrder reserveOrder);

    List<ReserveOrder> findAllOrdersByAccountId(Long account_id);

    List<ReserveOrder> findAllPaidOrdersByAccountId(Long account_id);

    List<ReserveOrder> getAllOrdersByAccount();

    void updateOrderDetails(ReserveOrder order, Long paidOrderID);

    ReserveOrder findAllNewOrdersByAccountIdAndItemId(Long account_id, Long item_id);

    void deleteAllOrders();

    Invoice getInvoice(ReserveOrder order);

    ReserveOrder get(Long order_id);

    boolean isReservedByCurrentAccount(String accountName, ReserveOrder orderId);

    void delete(ReserveOrder reserveOrder);

    boolean currentOrderAvailableForAccountId(Long accountId);

    void deleteOrdersForAccountId(Long accountId);

    int getQuantityOfItemsInCart(Long accountId);

    ReserveOrder createReserveOrder(Account account, Item item);
}
