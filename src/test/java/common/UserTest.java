package common;

import com.example.dingko.common.domain.UserVO;
import com.example.dingko.common.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j2
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    public void insertTest(){
        UserVO userVO = new UserVO();
        userVO.setUserId("test1");
        userVO.setUserPw("test1");
        userVO.setUserNm("테스트아이디");
        userVO.setUserEmail("@@@");
        userVO.setUserPhoneNum("010");
        userVO.setFstRgttId("kys");

        userService.insertUser(userVO);
    }

    @Test
    public void getUserTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "test");
        UserVO userVO = userService.getUser(map);

        System.out.println(userVO);
    }
}
