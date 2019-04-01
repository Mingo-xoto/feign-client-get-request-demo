package com.xiaoxuan.actuator.controller;

import com.xiaoxuan.api.IMyController;
import com.xiaoxuan.dto.MyEndpointDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MyController implements IMyController {

    @Override
    public MyEndpointDto get1(MyEndpointDto dto){
        dto.setName("fuck you");
        return dto;
    }

    @Override
    public MyEndpointDto get0(int id, String name) {
        MyEndpointDto dto = new MyEndpointDto();
        dto.setName(name);
        dto.setId(id);
        return dto;
    }

    @Override
    public MyEndpointDto get3(int id, int age) {
        MyEndpointDto dto = new MyEndpointDto();
        dto.setName("xx"+age+"岁");
        dto.setId(id);
        return dto;
    }

    @Override
    public MyEndpointDto get4(int id) {
        MyEndpointDto dto = new MyEndpointDto();
        dto.setId(id);
        return dto;
    }

    @Override
    public MyEndpointDto get2() {
        MyEndpointDto dto = new MyEndpointDto();
        dto.setName("faker....");
        return dto;
    }

    @Override
    public MyEndpointDto get5(MyEndpointDto dto) {
        MyEndpointDto.InnerDto innerDto = dto.getAgeSex();
        if(innerDto != null){
            innerDto.setAge(innerDto.getAge()*10);
        }
        return dto;
    }

    @Override
    public MyEndpointDto post(@RequestBody MyEndpointDto dto) {
        return dto;
    }

    @Override
    public MyEndpointDto map(@RequestBody Map<String,String> map){
        MyEndpointDto dto = new MyEndpointDto();
        dto.setName(map.get("name"));
        return dto;
    }

    @Override
    public MyEndpointDto get6(@RequestBody MyEndpointDto[] dtos) {
        MyEndpointDto dto = new MyEndpointDto();
        return dto;
    }

    @Override
    public MyEndpointDto get7(@RequestParam("list") String[] list) {
        MyEndpointDto dto = new MyEndpointDto();
        return dto;
    }
}
