package com.example.dingko.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PERSISTANCE LAYER 영역으로 전달되는 파라미터 값에 대한 전달용 맵 객체
 * 사용방법) 본 객체는 싱클톤 객체, getInstance로 생성 객체를 로드한 후
 * Map 사용 시 put 메서드 대신에 setXXX를 사용하여 객체 저장 후 최종적으로 getParameterMap을 호출하여
 * Map<String, Object> Type의 객체를 생성한다.
 * Map 객체와 다른 점은 chain 방식으로 사용할 수 있다.
 * ex) HashParameterMap.getInstance().setString("key", "value").setInt("key", 1).getParameterMap();
 * */
public class HashParameterMap {
    private HashMap<String, Object> paramegerMap;

    public HashParameterMap(){
        this.paramegerMap = new HashMap<>();
    }

    public Map<String, Object> getParameterMap(){
        return this.paramegerMap;
    }

    public HashParameterMap clear(){
        this.paramegerMap.clear();
        return this;
    }

    public HashParameterMap setString(String key, String value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setInt(String key, int value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setLong(String key, long value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setChar(String key, char value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setDouble(String key, double value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setFloat(String key, float value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setByte(String key, byte value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setMap(String key, Map<String, Object> value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setList(String key, List<? extends Object> value){
        this.paramegerMap.put(key, value);
        return this;
    }
    public HashParameterMap setObject(String key, Object value){
        this.paramegerMap.put(key, value);
        return this;
    }
}
