package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.dao.UserDao;
import com.example.ems_thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class UserServiceImpl  implements UserService{

    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }


    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public User login(String user_name, String password) {
        User user = userDao.findByUserName(user_name);
        if (ObjectUtils.isEmpty(user))throw new RuntimeException("用户名不正确");
        if (!user.getPassword().equals(password))throw new RuntimeException("密码输入错误！");
        return user;
    }

    @Override
    public boolean isUserValid(String user_name, String password) {
        User user = userDao.findByUserName(user_name);
        return user!=null&&user.getPassword().equals(password);
    }

    @Override
    public boolean isUserExisted(String user_name) {
        User userOB = userDao.findByUserName(user_name);
        return userOB!=null;
    }

    @Override
    public boolean isPasswordValid(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{6,15}$";
        return password.matches(regex);
    }


}
