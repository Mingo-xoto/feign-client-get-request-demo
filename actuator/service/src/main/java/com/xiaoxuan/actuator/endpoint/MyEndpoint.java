package com.xiaoxuan.actuator.endpoint;

import com.xiaoxuan.dto.MyEndpointDto;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 自定义endpoint
 * @Author : YHQ
 * @Date: 10:27 2019/3/22
 *
 */
@WebEndpoint(id="my")
@Configuration
public class MyEndpoint {

    {
        System.out.println("config MyEndpoint");
    }

    /**
     * http调用方式:发送get请求：http://domain/actuator/my/{id}/{name}
     * @Author : YHQ
     * @Date: 10:19 2019/3/22
     *
     */
    @ReadOperation
    public MyEndpointDto read(@Selector Integer id, @Selector String name){
        MyEndpointDto dto = new MyEndpointDto();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }

    /**
     * http调用方式:发送get请求：http://domain/actuator/my
     * @Author : YHQ
     * @Date: 10:19 2019/3/22
     *
     */
    @ReadOperation
    public MyEndpointDto read2(){
        return new MyEndpointDto();
    }

    /**
     * http调用方式:发送post请求：http://domain/actuator/my
     * @Author : YHQ
     * @Date: 10:19 2019/3/22
     *
     */
    @WriteOperation
    public MyEndpointDto wirte(Map<String,String> map){
        return null;
    }

    /**
     * http调用方式:发送delete请求：http://domain/actuator/my
     * @Author : YHQ
     * @Date: 10:19 2019/3/22
     *
     */
    @DeleteOperation
    public String delete(){
        return "delete test";
    }

}
