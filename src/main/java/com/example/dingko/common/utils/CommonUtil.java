package com.example.dingko.common.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 프로젝트 공통 유틸
 * */
@Log4j2
public class CommonUtil {
    /**
     * HttpServletRequest 에 담긴 요소를 반환
     * */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestHolder =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestHolder.getRequest();
    }

    /**
     * REQUEST에 담겨있는 값을 HashParameterMap에 담아서 반환
     * VO를 사용하지 않고 Map으로 파라미터를 넘길 경우에 사용
     * @return HashParameterMap
     * */
    public static HashParameterMap setRequestParameterMap(){
        HashParameterMap params = new HashParameterMap();

        Enumeration<String> names = getRequest().getParameterNames();
        while(names.hasMoreElements()){
            String name = (String) names.nextElement();
            params.setObject(name, getRequest().getParameter(name));
        }

        return params;
    }

    /**Spring Security 세션 정보 얻기*/
    public static Authentication getAuthUserInfo(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && !"anonymousUser".equals(authentication.getName())){
                return authentication;
            } else {
                return null;
            }
        } catch (Exception e){
            log.info("CommonUtil : 시큐리티 UserInfo 얻는 도중 오류 발생");
            e.printStackTrace();
            return null;
        }
    }
}
