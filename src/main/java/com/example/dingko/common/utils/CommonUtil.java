package com.example.dingko.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 프로젝트 공통 유틸
 * */
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
}
