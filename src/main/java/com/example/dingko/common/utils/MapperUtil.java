package com.example.dingko.common.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * mybatis mapper xml 파일 내 dynamic query 적용 시 값의 유효성 체크 시 사용되는 유틸리티
 * */
public class MapperUtil {
    /**
     * 스트링 유형의 공백과 null이 아니면 true
     */
    public static boolean isNotBlank(String value) {
        return StringUtils.isNotBlank(value);
    }
}
