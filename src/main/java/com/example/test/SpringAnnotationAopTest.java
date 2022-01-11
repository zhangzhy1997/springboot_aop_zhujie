package com.example.test;

import com.example.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class SpringAnnotationAopTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        userService.save();
    }
}
