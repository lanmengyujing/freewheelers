package com.trailblazers.freewheelers.model;

import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.util.Date;

import static com.google.common.base.Objects.equal;

public class ReserveOrder {

    private Long order_id;
    private Long account_id;
    private Long item_id;
    private String transactionId;
    private String note = "";

    private OrderStatus status = OrderStatus.NEW;
    private Date reservation_timestamp;
    private Account account;
    private Item item;
    private long item_quantity;
    private CreditCardPayment creditCardPayment;

    private BigDecimal price;
    private BigDecimal dutyTax;
    private BigDecimal vatTax;

    public ReserveOrder() {
    }

    public ReserveOrder(Long account_id, Long item_id, Date rightNow) {
        this.account_id = account_id;
        this.item_id = item_id;
        this.reservation_timestamp = rightNow;
    }

    public Long getItem_id() {
        return item_id;
    }

    public ReserveOrder setItem_id(Long item_id) {
        this.item_id = item_id;
        return this;
    }

    public Date getReservation_timestamp() {
        return reservation_timestamp;
    }

    public ReserveOrder setReservation_timestamp(Date reservation_timestamp) {
        this.reservation_timestamp = reservation_timestamp;
        return this;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public ReserveOrder setOrder_id(Long order_id) {
        this.order_id = order_id;
        return this;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public ReserveOrder setAccount_id(Long account_id) {
        this.account_id = account_id;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ReserveOrder setNote(String note) {
        this.note = note;
        return this;
    }

    public String getNote() {
        return note;
    }

    public ReserveOrder setItem_quantity(long item_quantity) {
        this.item_quantity = item_quantity;
        return this;
    }

    public long getItem_quantity() {
        return item_quantity;
    }

    public ReserveOrder setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public Item getItem() {
        return item;
    }

    public ReserveOrder setAccount(Account account) {
        this.account = account;
        return this;
    }

    public ReserveOrder setItem(Item item) {
        this.item = item;
        return this;
    }

    public OrderStatus[] getStatusOptions() {
        return OrderStatus.values();
    }

    public CreditCardPayment getCreditCardPayment() {
        return creditCardPayment;
    }

    public ReserveOrder setCreditCardPayment(CreditCardPayment creditCardPayment) {
        this.creditCardPayment = creditCardPayment;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ReserveOrder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getDutyTax() {
        return dutyTax;
    }

    public ReserveOrder setDutyTax(BigDecimal dutyTax) {
        this.dutyTax = dutyTax;
        return this;
    }

    public BigDecimal getVatTax() {
        return vatTax;
    }

    public ReserveOrder setVatTax(BigDecimal vatTax) {
        this.vatTax = vatTax;
        return this;
    }

    public BigDecimal getTotal() {
        return new BigDecimal(0).add(price).add(dutyTax).add(vatTax);
    }

    public boolean isPaymentSuccessful() {
        return getCreditCardPayment().getPaymentStatus().isSuccess();
    }

    @Override
    public boolean equals(Object o) {

            if (this == o) return true;
            if (!(o instanceof ReserveOrder)) return false;

            ReserveOrder anotherReserveOrder = (ReserveOrder) o;

            return equal(order_id, anotherReserveOrder.getOrder_id()) &&
                    equal(account_id, anotherReserveOrder.getAccount_id()) &&
                    equal(item_id, anotherReserveOrder.getItem_id()) &&
                    equal(transactionId, anotherReserveOrder.getTransactionId()) &&
                    equal(note, anotherReserveOrder.getNote()) &&
                    equal(status, anotherReserveOrder.getStatus()) &&
                    equal(reservation_timestamp, anotherReserveOrder.getReservation_timestamp()) &&
                    equal(account, anotherReserveOrder.getAccount()) &&
                    equal(item, anotherReserveOrder.getItem()) &&
                    equal(creditCardPayment, anotherReserveOrder.getCreditCardPayment()) &&
                    equal(price, anotherReserveOrder.getPrice()) &&
                    equal(dutyTax, anotherReserveOrder.getDutyTax()) &&
                    equal(vatTax, anotherReserveOrder.getVatTax());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(account_id, item_id, transactionId, note, status, reservation_timestamp, account, item, creditCardPayment, price, dutyTax, vatTax);
    }

    boolean isReadyForShipment() {
        return getStatus() != OrderStatus.READY_FOR_SHIPMENT;
    }
}
