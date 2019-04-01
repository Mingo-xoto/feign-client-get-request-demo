/*
package com.xiaoxuan.utils;

import com.xiaoxuan.dto.MyEndpointDto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanPropertiesUtil {

    public static Map<Long, Map<Integer, Integer[]>> indexMap = new HashMap<>();

    public static <T> String toString2(T t, Class<T> tClass) {
        Long threadId = Thread.currentThread().getId();
        Integer entityHashCode = t.hashCode();
        Map<Integer, Integer[]> map = indexMap.get(threadId);
        if (map == null) {
            map = new HashMap<>();
            indexMap.put(threadId, map);
        }
        Integer indexs[] = map.get(entityHashCode);
        for (Integer index:indexs) {
            Field[] fields = tClass.getDeclaredFields();
            for(){

            }
            Field field = fields[index];
            field.setAccessible(true);
            map.put(entityHashCode, index);
            try {
                Object value = field.get(t);
                System.out.println(value);
                return value + "";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        MyEndpointDto dto = new MyEndpointDto();
        dto.setName("faker");
        dto.setId("jsjs");
        System.out.println(toString2(dto, MyEndpointDto.class));
        System.out.println(toString2(dto, MyEndpointDto.class));
        System.out.println(toString2(dto, MyEndpointDto.class));
        System.out.println(toString2(dto, MyEndpointDto.class));
    }
}
*/
