package com.day05.dao;

import com.day05.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findUserById(int id);
    List<User> findByIds(List<Integer> ids);
    void updateSql(User user);

}
