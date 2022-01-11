package com.example.service;

public interface UserService {
    void save();
    void save(String userId)throws Exception;
    void update();
    void delete();
    void find();
}
