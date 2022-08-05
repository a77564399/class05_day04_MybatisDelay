package com.day05.dao;

import com.day05.domain.Account;
import com.day05.domain.AccountUser;
import com.day05.domain.User;
import com.day05.domain.UserAccount;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户，同时还要获取到当前账户的所有信息
     */
    List<AccountUser> findAccountUser();

    /**
     * 查询所有用户的所有账户，无账户的用户返回用户，账户设置为空
     */
    List<UserAccount> findUserAccount();


}
