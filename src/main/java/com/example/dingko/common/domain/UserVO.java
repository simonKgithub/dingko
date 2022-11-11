package com.example.dingko.common.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("userVO")
public class UserVO {
    private String userId;
    private String userPw;
    private String userNm;
    private String userPhoneNum;
    private String userEmail;
    private String fstRgttId;
    private String fstRgstDt;
    private String fnlAmdId;
    private String fnlMofDt;
}
