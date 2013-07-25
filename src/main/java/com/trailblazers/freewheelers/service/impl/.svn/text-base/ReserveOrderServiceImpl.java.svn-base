package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mail.HtmlMailBuilder;
import com.trailblazers.freewheelers.mail.OrderNotificationMailContent;
import com.trailblazers.freewheelers.mappers.*;
import com.trailblazers.freewheelers.model.*;
import com.trailblazers.freewheelers.service.MailService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.TaxService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReserveOrderServiceImpl implements ReserveOrderService {

    private final SqlSession sqlSession;
    private final ReserveOrderMapper orderMapper;
    private final AccountMapper accountMapper;
    private final ItemMapper itemMapper;
    private TaxService taxService;
    private PaidOrderMapper paidOrderMapper;

    @Autowired
    private MailService mailService;

    public void setStatusMailBuilder(HtmlMailBuilder statusMailBuilder) {
        this.statusMailBuilder = statusMailBuilder;
    }

    @Autowired
    @Qualifier("orderStatusChangingToReadyToShipmentMailBuilder")
    private HtmlMailBuilder statusMailBuilder;


    public ReserveOrderServiceImpl() {
        this(MyBatisUtil.getSqlSession());
    }

    protected ReserveOrderServiceImpl(ReserveOrderMapper orderMapper, AccountMapper accountMapper, ItemMapper itemMapper, TaxService taxService, PaidOrderMapper paidOrderMapper) {
        this.orderMapper = orderMapper;
        this.accountMapper = accountMapper;
        this.itemMapper = itemMapper;
        this.taxService = taxService;
        this.paidOrderMapper = paidOrderMapper;
        this.sqlSession = null;
    }

    public ReserveOrderServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        orderMapper = sqlSession.getMapper(ReserveOrderMapper.class);
        accountMapper = sqlSession.getMapper(AccountMapper.class);
        itemMapper = sqlSession.getMapper(ItemMapper.class);
        paidOrderMapper = sqlSession.getMapper(PaidOrderMapper.class);
        taxService = new TaxServiceImpl();
    }

    public void save(ReserveOrder reserveOrder) {
        if (reserveOrder.getOrder_id() == null) {
            orderMapper.insert(reserveOrder);
        } else {
            orderMapper.save(reserveOrder);
        }
        sqlSession.commit();

    }

    public List<ReserveOrder> findAllOrdersByAccountId(Long account_id) {
        sqlSession.clearCache();

        List<ReserveOrder> reserveOrders = orderMapper.findAllFor(account_id);
        return reserveOrders;
    }

    public List<ReserveOrder> findAllPaidOrdersByAccountId(Long account_id) {
        sqlSession.clearCache();

        List<ReserveOrder> reserveOrders = orderMapper.findAllPaidItemFor(account_id);
        return reserveOrders;
    }

    @Override
    public ReserveOrder findAllNewOrdersByAccountIdAndItemId(Long account_id, Long item_id) {
        sqlSession.clearCache();

        ReserveOrder reserveOrder = orderMapper.findAllNewForAccountAndItem(account_id, item_id);

        return reserveOrder;
    }

    public List<ReserveOrder> getAllOrdersByAccount() {
        sqlSession.clearCache();
        List<ReserveOrder> reserveOrders = orderMapper.findAll();
        return reserveOrders;
    }

    public void updateOrderDetails(ReserveOrder order, Long paidOrderID) {
        ReserveOrder reserveOrder = orderMapper.get(order.getOrder_id());

        reserveOrder.setStatus(order.getStatus());
        reserveOrder.setNote(order.getNote());
        reserveOrder.setTransactionId(order.getTransactionId());

        PaidOrder paidOrder = paidOrderMapper.getByPaidOrderId(paidOrderID.intValue());
        if (paidOrder.getStatus() == OrderStatus.READY_FOR_SHIPMENT){
            mailService.send(statusMailBuilder, new OrderNotificationMailContent(paidOrder));
        }
        orderMapper.save(reserveOrder);
        sqlSession.commit();

    }


    @Override
    public void deleteAllOrders() {
        sqlSession.clearCache();
        orderMapper.deleteAll();
        sqlSession.commit();
    }

    @Override
    public void delete(ReserveOrder reserveOrder) {
        orderMapper.delete(reserveOrder);
        sqlSession.commit();
    }

    @Override
    public boolean currentOrderAvailableForAccountId(Long accountId) {
        List<ReserveOrder> ordersByAccountId = findAllOrdersByAccountId(accountId);
        for (ReserveOrder reserveOrder : ordersByAccountId) {
            Long itemId = reserveOrder.getItem_id();
            if (itemMapper.get(itemId).getQuantity() < 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void deleteOrdersForAccountId(Long accountId) {
        List<ReserveOrder> ordersByAccountId = findAllOrdersByAccountId(accountId);
        for (ReserveOrder reserveOrder : ordersByAccountId) {
            orderMapper.delete(reserveOrder);
        }
    }

    @Override
    public int getQuantityOfItemsInCart(Long accountId) {
        List<ReserveOrder> reserveOrders = findAllOrdersByAccountId(accountId);

        int quantity = 0;

        for (ReserveOrder order : reserveOrders) {
            quantity += order.getItem_quantity();
        }

        return quantity;
    }

    @Override
    public ReserveOrder createReserveOrder(Account account, Item item) {
        ReserveOrder reserveOrder = new ReserveOrder(account.getAccount_id(), item.getItemId(), new Date());
        ReserveOrder existingOrder = findAllNewOrdersByAccountIdAndItemId(account.getAccount_id(), item.getItemId());

        if (existingOrder == null) {
            reserveOrder.setItem_quantity(1);
        } else {
            reserveOrder.setOrder_id(existingOrder.getOrder_id());
            reserveOrder.setItem_quantity(existingOrder.getItem_quantity() + 1);
        }
        return reserveOrder;
    }


    @Override
    public ReserveOrder get(Long order_id) {
        sqlSession.clearCache();
        try {
            return orderMapper.get(order_id);
        } catch (RuntimeException e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.commit();
        }
    }

    @Override
    public boolean isReservedByCurrentAccount(String accountName, ReserveOrder order) {
        Long accountID = accountMapper.getByName(accountName).getAccount_id();

        return accountID.equals(order.getAccount_id());
    }

    @Override
    public Invoice getInvoice(ReserveOrder order) {
        Invoice invoice = new Invoice();
        Account account = accountMapper.getById(order.getAccount_id());
        Item item = itemMapper.get(order.getItem_id());

        String country = account.getAddress().getCountry();
        double vatRate = taxService.getVatRate(country).doubleValue();
        BigDecimal vat = taxService.getVat(country, item.getPrice());
        BigDecimal dutyTax = taxService.getDutyTax(country, item.getPrice());

        InvoiceItem invoiceItem = getInvoiceItem(item, vat, vatRate);

        invoice.setAccount(account);
        invoice.addInvoiceItem(invoiceItem);

        invoice.setInvoiceNumber("INV-" + order.getOrder_id());
        invoice.setOrderId(order.getOrder_id());
        invoice.setNetTotal(invoiceItem.getNet());
        invoice.setVatTotal(invoiceItem.getVat());
        invoice.setDutyTax(dutyTax);
        invoice.setGrossTotal(dutyTax.add(vat).add(item.getPrice()));
        return invoice;
    }

    private InvoiceItem getInvoiceItem(Item item, BigDecimal vat, double vatRate) {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setName(item.getName())
                .setUnitPrice(item.getPrice())
                .setVatRate(vatRate)
                .setVat(vat)
                .setQuantity(new Long(1))
                .setNet(item.getPrice())
                .setGross(vat.add(item.getPrice()));
        return invoiceItem;
    }

    public void setMailService(MailServiceImpl mailService) {
        this.mailService = mailService;
    }
}
