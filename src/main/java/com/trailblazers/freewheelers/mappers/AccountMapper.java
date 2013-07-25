package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountMapper {

    @Insert(
            "INSERT INTO account (account_name, email_address, password, phone_number, street_one, street_two, state, city, country, zip, enabled) " +
                    "VALUES (#{account_name}, #{emailAddress}, #{password}, #{phoneNumber}, #{address.streetOne}, #{address.streetTwo}, #{address.state}, #{address.city}, #{address.country}, #{address.zip}, #{enabled})"
    )
    @Options(keyProperty = "account_id", useGeneratedKeys = true)
    Integer insert(Account account);

    @Select(
            "SELECT account_id, account_name, email_address, password, phone_number, street_one, street_two, state, city, country, zip, enabled " +
                    "FROM account " +
                    "WHERE account_id = #{account_id}"
    )
    @Results(value = {
            @Result(property = "account_id"),
            @Result(property = "account_name"),
            @Result(property = "emailAddress", column = "email_address"),
            @Result(property = "password"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "address.streetOne", column = "street_one"),
            @Result(property = "address.streetTwo", column = "street_two"),
            @Result(property = "address.city", column = "city"),
            @Result(property = "address.state", column = "state"),
            @Result(property = "address.country", column = "country"),
            @Result(property = "address.zip", column = "zip")
    })
    Account getById(Long account_id);

    @Select(
            "SELECT account_id, account_name, email_address, password, phone_number, street_one, street_two, state, city, country, zip, enabled " +
                    "FROM account " +
                    "WHERE account_name = #{account_name} " +
                    "LIMIT 1"
    )
    @Results(value = {
            @Result(property = "account_id"),
            @Result(property = "account_name"),
            @Result(property = "emailAddress", column = "email_address"),
            @Result(property = "password"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "address.streetOne", column = "street_one"),
            @Result(property = "address.streetTwo", column = "street_two"),
            @Result(property = "address.city", column = "city"),
            @Result(property = "address.state", column = "state"),
            @Result(property = "address.country", column = "country"),
            @Result(property = "address.zip", column = "zip")
    })
    Account getByName(String accountName);

    @Update(
            "UPDATE account " +
                    "SET account_name=#{account_name}, email_address=#{emailAddress}, " +
                    "phone_number=#{phoneNumber}, enabled=#{enabled}," +

                    "street_one=#{address.streetOne}, street_two=#{address.streetTwo}, city=#{address.city}, " +
                    "state=#{address.state}, country = #{address.country},zip = #{address.zip} " +
                    "WHERE account_id= #{account_id}"
    )
    void update(Account account);

    @Select(
            "SELECT * FROM account"
    )
    @Results(value = {
            @Result(property = "account_id"),
            @Result(property = "account_name"),
            @Result(property = "emailAddress", column = "email_address"),
            @Result(property = "password"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "address.streetOne", column = "street_one"),
            @Result(property = "address.streetTwo", column = "street_two"),
            @Result(property = "address.city", column = "city"),
            @Result(property = "address.state", column = "state"),
            @Result(property = "address.country", column = "country"),
            @Result(property = "address.zip", column = "zip")
    })
    public List<Account> findAll();

    @Delete(
            "DELETE FROM account WHERE account_id = #{account_id}"
    )
    @Options(flushCache = true)
    void delete(Account account);

}
