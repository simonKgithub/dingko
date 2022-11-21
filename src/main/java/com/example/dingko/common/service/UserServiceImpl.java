package com.example.dingko.common.service;

import com.example.dingko.common.domain.UserVO;
import com.example.dingko.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserVO getUser(Map<String, Object> params) {
        return userMapper.getUser(params);
    }

    @Override
    public boolean insertUser(UserVO userVO) {
        //인코딩
        userVO.setUserPw(passwordEncoder.encode(userVO.getUserPw()));
        int count = userMapper.insertUser(userVO);

        return count == 1;
    }
}
