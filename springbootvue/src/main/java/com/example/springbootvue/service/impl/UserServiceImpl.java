package com.example.springbootvue.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootvue.mapper.UserMapper;
import com.example.springbootvue.pojo.User;
import com.example.springbootvue.service.UserService;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
