package com.example.dingko.common.service;

import com.example.dingko.common.domain.UserVO;
import com.example.dingko.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public UserVO getUser(Map<String, Object> params) {
        return userMapper.getUser(params);
    }

    @Override
    public boolean insertUser(UserVO userVO) {
        int count = userMapper.insertUser(userVO);
        return count == 1;
    }
}
