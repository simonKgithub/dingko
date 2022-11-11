package com.example.dingko.common.mapper;

import com.example.dingko.common.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
public interface UserMapper {
    public UserVO getUser(Map<String, Object> params);

    public int insertUser(UserVO userVO);
}
