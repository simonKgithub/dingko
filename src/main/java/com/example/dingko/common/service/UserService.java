package com.example.dingko.common.service;

import com.example.dingko.common.domain.UserVO;
import com.example.dingko.common.mapper.UserMapper;

import java.util.Map;

public interface UserService {
    public UserVO getUser(Map<String, Object> params);

    public boolean insertUser(UserVO userVO);
}
