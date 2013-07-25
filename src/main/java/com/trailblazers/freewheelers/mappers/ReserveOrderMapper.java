package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.ReserveOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReserveOrderMapper {

    @Insert(
            "INSERT INTO reserve_order (account_id, item_id, item_quantity, status, note, transaction_id, reservation_timestamp) " +
                    "VALUES (#{account_id}, #{item_id}, #{item_quantity}, #{status}, #{note}, #{transactionId}, #{reservation_timestamp})"
    )
    @Options(keyProperty = "order_id", useGeneratedKeys = true)
    Integer insert(ReserveOrder order);

    @Select(
            "SELECT order_id, account_id, item_id, item_quantity, status, note, reservation_timestamp, transaction_id " +
                    "FROM reserve_order " +
                    "WHERE order_id = #{order_id}"
    )
    @Results(value = {
            @Result(property = "order_id"),
            @Result(property = "account_id"),
            @Result(property = "item_id"),
            @Result(property = "status"),
            @Result(property = "note"),
            @Result(property = "reservation_timestamp"),
            @Result(property = "transactionId", column = "transaction_id")
    })
    ReserveOrder get(Long order_id);

    @Delete(
            "DELETE FROM reserve_order WHERE order_id = #{order_id}"
    )
    void delete(ReserveOrder reserveOrder);

    @Update(
            "UPDATE reserve_order " +
                    "SET account_id=#{account_id}, item_id=#{item_id}, item_quantity=#{item_quantity}, status=#{status}, note=#{note}, reservation_timestamp=#{reservation_timestamp}, transaction_id=#{transactionId}  " +
                    "WHERE order_id=#{order_id}"
    )
    void save(ReserveOrder reserveOrder);

    @Select(
            "SELECT order_id, account_id, item_id, item_quantity, status, note, reservation_timestamp, transaction_id " +
                    "FROM reserve_order " +
                    "ORDER BY account_id"
    )
    @Results(value = {
            @Result(property = "order_id"),
            @Result(property = "account_id"),
            @Result(property = "item_id"),
            @Result(property = "item_quantity"),
            @Result(property = "status"),
            @Result(property = "note"),
            @Result(property = "reservation_timestamp"),
            @Result(property = "transactionId", column = "transaction_id")
    })
    List<ReserveOrder> findAll();

    @Select(
            "SELECT order_id, account_id, item_id, item_quantity, status, note, reservation_timestamp, transaction_id " +
                    "FROM reserve_order " +
                    "WHERE account_id=#{account_id}"
    )
    @Results(value = {
            @Result(property = "order_id"),
            @Result(property = "account_id"),
            @Result(property = "item_id"),
            @Result(property = "item_quantity"),
            @Result(property = "status"),
            @Result(property = "note"),
            @Result(property = "reservation_timestamp"),
            @Result(property = "transactionId", column = "transaction_id")
    })
    List<ReserveOrder> findAllFor(Long account_id);

    @Delete(
            "DELETE FROM reserve_order"
    )
    void deleteAll();

    @Select(
            "SELECT order_id, account_id, item_id, item_quantity, status, note, reservation_timestamp " +
                    "FROM reserve_order " +
                    "WHERE account_id=#{account_id} and item_id=#{item_id} and status='NEW'"
    )
    @Results(value = {
            @Result(property = "order_id"),
            @Result(property = "account_id"),
            @Result(property = "item_id"),
            @Result(property = "item_quantity"),
            @Result(property = "status"),
            @Result(property = "note"),
            @Result(property = "reservation_timestamp")
    })
    ReserveOrder findAllNewForAccountAndItem(@Param("account_id") Long account_id, @Param("item_id") Long item_id);


    @Select(
            "SELECT order_id, account_id, item_id, item_quantity, status, note, reservation_timestamp, transaction_id " +
                    "FROM reserve_order " +
                    "WHERE account_id=#{account_id} AND status!='NEW'"
    )
    @Results(value = {
            @Result(property="order_id"),
            @Result(property="account_id"),
            @Result(property="item_id"),
            @Result(property="item_quantity"),
            @Result(property="status"),
            @Result(property="note"),
            @Result(property="reservation_timestamp"),
            @Result(property = "transactionId", column = "transaction_id")
    })
    List<ReserveOrder> findAllPaidItemFor(Long account_id);
}
