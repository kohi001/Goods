package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.entity.User;

public interface UserDao {
    User findByUserName(String user_name);

    void save(User user);
}
