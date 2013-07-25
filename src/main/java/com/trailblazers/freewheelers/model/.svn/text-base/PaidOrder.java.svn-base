package com.trailblazers.freewheelers.model;

import java.math.BigDecimal;
import java.util.List;

public class PaidOrder {
    private List<ReserveOrder> orders;
    private Long account_id;
    private Integer pay_id;
    private BigDecimal net_total = new BigDecimal(0);
    private BigDecimal gross_total = new BigDecimal(0);
    private BigDecimal vat = new BigDecimal(0);
    private BigDecimal duty_tax = new BigDecimal(0);

    public void setOrders(List<ReserveOrder> orders) {
        this.orders = orders;
    }

    public long getAccount_id() {
        return account_id;
    }

    public Integer getPay_id() {
        return pay_id;
    }

    public BigDecimal getNet_total() {
        return net_total;
    }

    public BigDecimal getGross_total() {
        return gross_total;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public BigDecimal getDuty_tax() {
        return duty_tax;
    }

    public void setNet_total(BigDecimal net_total) {
        this.net_total = net_total;
    }

    public void setGross_total(BigDecimal gross_total) {
        this.gross_total = gross_total;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }


    public void setDuty_tax(BigDecimal duty_tax) {
        this.duty_tax = duty_tax;
    }

    public List<ReserveOrder> getOrders() {
        return orders;
    }

    public void setPay_id(Integer pay_id) {
        this.pay_id = pay_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaidOrder paidOrder = (PaidOrder) o;

        if (account_id != null ? !account_id.equals(paidOrder.account_id) : paidOrder.account_id != null) return false;
        if (duty_tax != null ? !duty_tax.equals(paidOrder.duty_tax) : paidOrder.duty_tax != null) return false;
        if (gross_total != null ? !gross_total.equals(paidOrder.gross_total) : paidOrder.gross_total != null)
            return false;
        if (net_total != null ? !net_total.equals(paidOrder.net_total) : paidOrder.net_total != null) return false;
        if (orders != null ? !orders.equals(paidOrder.orders) : paidOrder.orders != null) return false;
        if (pay_id != null ? !pay_id.equals(paidOrder.pay_id) : paidOrder.pay_id != null) return false;
        if (vat != null ? !vat.equals(paidOrder.vat) : paidOrder.vat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orders != null ? orders.hashCode() : 0;
        result = 31 * result + (account_id != null ? account_id.hashCode() : 0);
        result = 31 * result + (pay_id != null ? pay_id.hashCode() : 0);
        result = 31 * result + (net_total != null ? net_total.hashCode() : 0);
        result = 31 * result + (gross_total != null ? gross_total.hashCode() : 0);
        result = 31 * result + (vat != null ? vat.hashCode() : 0);
        result = 31 * result + (duty_tax != null ? duty_tax.hashCode() : 0);
        return result;
    }

    public OrderStatus getStatus() {
        for(ReserveOrder order : orders) {
            if(order.isReadyForShipment()) {
                return OrderStatus.PAID;
            }
        }
        return OrderStatus.READY_FOR_SHIPMENT;
    }

}
