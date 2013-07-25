package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.PaidOrder;
import com.trailblazers.freewheelers.model.ReserveOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PaidOrderMapper {


    @Insert(
            "INSERT INTO paid_order_info (account_id, net_total, gross_total, vat, duty_tax) " +
                    "VALUES (#{account_id}, 0, 0, 0, 0)"
    )
    @Options(keyProperty = "pay_id", useGeneratedKeys = true)
    Integer savePaidOrderInfo(PaidOrder paidOrder);

    @Select(
            "SELECT max(pay_id) from paid_order_info"
    )
    Integer getLastPayId();

    @Select(
            "SELECT  pay_id, account_id, net_total, gross_total, vat, duty_tax " +
                    "FROM paid_order_info " +
                    "WHERE pay_id = #{pay_id}"
    )
    @Results(value = {
            @Result(property = "pay_id", column = "pay_id"),
            @Result(property = "account_id"),
            @Result(property = "orders", column = "pay_id", javaType = List.class,
                    many = @Many(select = "getReserveItems")),
            @Result(property = "net_total"),
            @Result(property = "gross_total"),
            @Result(property = "vat"),
            @Result(property = "duty_tax")
    })
    PaidOrder getByPaidOrderId(int pay_id);

    @Insert(
            "INSERT INTO paid_order_items (pay_id, order_id, account_id, item_id, item_quantity, status, note, reservation_timestamp, transaction_id) " +
                    "VALUES (#{pay_id}, #{reserveOrder.order_id}, #{reserveOrder.account_id}, #{reserveOrder.item_id}, #{reserveOrder.item_quantity}, #{reserveOrder.status}, #{reserveOrder.note}, #{reserveOrder.reservation_timestamp}, #{reserveOrder.transactionId})"
    )
    void  saveReservedItem(@Param("reserveOrder") ReserveOrder reserveOrder, @Param("pay_id") Integer pay_id);

    @Select(
            "SELECT order_id, account_id, item_id, item_quantity, status, note, reservation_timestamp, transaction_id " +
                    "FROM paid_order_items " +
                    "WHERE pay_id = #{pay_id}"
    )

    @Results(value = {
            @Result(property = "order_id"),
            @Result(property = "account_id", column = "account_id"),
            @Result(property = "account", column = "account_id", javaType = Account.class,
                    one = @One(select = "com.trailblazers.freewheelers.mappers.AccountMapper.getById")),
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "item", column = "item_id", javaType = Item.class,
                    one = @One(select = "com.trailblazers.freewheelers.mappers.ItemMapper.get")),
            @Result(property = "item_quantity"),
            @Result(property = "status", column = "status"),
            @Result(property = "note"),
            @Result(property = "transactionId", column = "transaction_id"),
            @Result(property = "reservation_timestamp")
    })
    List<ReserveOrder> getReserveItems(Integer pay_id);

    @Select(
            "SELECT pay_id, account_id, net_total, gross_total, vat, duty_tax " +
                    "FROM paid_order_info "
    )
    @Results(value = {
            @Result(property = "pay_id", column = "pay_id"),
            @Result(property = "orders", column = "pay_id", javaType = List.class,
                    many = @Many(select = "getReserveItems")),
            @Result(property = "account_id"),
            @Result(property = "net_total"),
            @Result(property = "gross_total"),
            @Result(property = "vat"),
            @Result(property = "duty_tax")
            })
    List<PaidOrder> getAllPaidOrders();
}
