package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface AccountRoleMapper {

    @Insert(
            "INSERT INTO account_role (account_name, role) VALUES (#{account_name}, #{role})"
    )
    @Options(keyProperty = "role_id", useGeneratedKeys = true)
    void insert(AccountRole accountRole);


    @Delete(
            "DELETE FROM account_role WHERE account_name = #{account_name}"
    )
    @Options(flushCache = true)
    void delete(Account account);
}
