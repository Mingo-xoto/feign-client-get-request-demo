package com.xiaoxuan.api;

import com.xiaoxuan.dto.MyEndpointDto;
import constants.EurekaConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@ApiModel("测试feign")
@FeignClient(name = EurekaConstants.APPLICATION_NAME)
public interface IMyController {

    @ApiOperation("get请求")
    @RequestMapping(value = "get1",method = RequestMethod.GET)
    MyEndpointDto get1(MyEndpointDto dto);

    @ApiOperation("get请求")
    @RequestMapping(value = "get5",method = RequestMethod.GET)
    MyEndpointDto get5(MyEndpointDto dto);

    @ApiOperation("get请求")
    @RequestMapping(value = "get6",method = RequestMethod.POST)
    MyEndpointDto get6(@RequestBody MyEndpointDto[] dtos);

    @ApiOperation("get请求")
    @RequestMapping(value = "get7",method = RequestMethod.GET)
    MyEndpointDto get7(@RequestParam("list") String[] list);

    @ApiOperation("get请求")
    @RequestMapping(value = "get0",method = RequestMethod.GET)
    MyEndpointDto get0(@RequestParam("id")int id,@RequestParam("name") String name);

    @ApiOperation("get请求")
    @RequestMapping(value = "get3",method = RequestMethod.GET)
    MyEndpointDto get3(@RequestParam("id") int id,@RequestParam("name") int age);

    @ApiOperation("get请求")
    @RequestMapping(value = "get4",method = RequestMethod.GET)
    MyEndpointDto get4(int id);

    @ApiOperation("get请求")
    @RequestMapping(value = "get2",method = RequestMethod.GET)
    MyEndpointDto get2();

    @ApiOperation("get请求")
    @RequestMapping(value = "post",method = RequestMethod.POST)
    MyEndpointDto post(@RequestBody MyEndpointDto dto);

    @ApiOperation("map请求")
    @PostMapping("map")
    MyEndpointDto map(@RequestBody Map<String,String> map);

}
