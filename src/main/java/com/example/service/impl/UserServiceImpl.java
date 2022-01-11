package com.example.service.impl;

import com.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("保存用户。。。无参数");
    }

    @Override
    public void save(String userId) throws Exception {
        int i = 1 / 0;
        System.out.println("保存用户.....(有参数)");
    }

    @Override
    public void update() {
        System.out.println("更新用户....");
    }

    @Override
    public void delete() {
        System.out.println("删除用户....");
    }

    @Override
    public void find() {
        System.out.println("查找用户");
    }
}
