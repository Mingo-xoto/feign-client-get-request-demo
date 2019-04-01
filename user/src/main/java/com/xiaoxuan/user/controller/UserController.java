package com.xiaoxuan.user.controller;

import com.xiaoxuan.api.IMyController;
import com.xiaoxuan.dto.MyEndpointDto;
import com.xiaoxuan.dto.TestListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IMyController myController;

    @RequestMapping("get")
    public MyEndpointDto get(){
        MyEndpointDto dto = new MyEndpointDto();
        dto.setId(123);
        dto.setName("faker");
        MyEndpointDto.InnerDto innerDto = new MyEndpointDto.InnerDto();
        innerDto.setAge(10);
//        innerDto.setSex("男");
        List<Integer> priceList = new ArrayList<>();
        priceList.add(1);
        priceList.add(2);
        dto.setPriceList(priceList);

        Integer[] prices = new Integer[2];
        prices[0] = 3;
        prices[1] = 4;
//        dto.setPrices(prices);

        List<String> remarkList = new ArrayList<>();
        remarkList.add("雕娜星");
        remarkList.add("玩还是说");
        dto.setRemarkList(remarkList);
        String[] remarks = new String[2];
        remarks[0] = "很多很多";
        remarks[1] = "神经计算机";
        dto.setRemarks(remarks);

        dto.setAgeSex(innerDto);
        dto.setBirthDay(new Date());
        List<TestListDto> testListDtoList =new ArrayList<>();
        testListDtoList.add(TestListDto.init("天河",1));
        testListDtoList.add(TestListDto.init("越秀",2));
        dto.setTestListDtoList(testListDtoList);
        TestListDto[] testListDtos = new TestListDto[2];
        testListDtos[0] = TestListDto.init("黄埔",3);
        testListDtos[1] = TestListDto.init("番禺",4);
        testListDtos[1].setAddress(null);
        dto.setTestListDtos(testListDtos);

        myController.get2();
        MyEndpointDto[] dtos = new MyEndpointDto[1];
        dtos[0] = dto;

        myController.get6(dtos);
        String[] list = new String[2];
        list[0] = "7";
        list[1] = "8";
        myController.get7(list);

        MyEndpointDto get5 = myController.get5(dto);

        return get5;
    }

    @RequestMapping("get2")
    public String get2(){
        return "";
    }
}
