package com.xiaoxuan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel(value = "测试dto")
public class MyEndpointDto {

    @ApiModelProperty("id值")
    private int id;

    @ApiModelProperty("姓名")
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS",timezone = "Asia/Shanghai")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    @ApiModelProperty("生日")
    private Date birthDay;

    @ApiModelProperty("性别年龄")
    private InnerDto ageSex;

    @ApiModelProperty("价格列表")
    private List<Integer> priceList;

    @ApiModelProperty("价格数组")
    private Integer[] prices;

    @ApiModelProperty("备注列表")
    private List<String> remarkList;

    @ApiModelProperty("备注数组")
    private String[] remarks;

    @ApiModelProperty("测试列表")
    private List<TestListDto> testListDtoList;

    @ApiModelProperty("测试数组")
    private TestListDto[] testListDtos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InnerDto getAgeSex() {
        return ageSex;
    }

    public void setAgeSex(InnerDto ageSex) {
        this.ageSex = ageSex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public static class InnerDto{

        @ApiModelProperty("年龄")
        private int age;

        @ApiModelProperty("性别")
        private String sex;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public InnerDto() {
        }
    }


    public List<String> getRemarkList() {
        return remarkList;
    }

    public void setRemarkList(List<String> remarkList) {
        this.remarkList = remarkList;
    }

    public String[] getRemarks() {
        return remarks;
    }

    public void setRemarks(String[] remarks) {
        this.remarks = remarks;
    }

    public List<TestListDto> getTestListDtoList() {
        return testListDtoList;
    }

    public void setTestListDtoList(List<TestListDto> testListDtoList) {
        this.testListDtoList = testListDtoList;
    }

    public TestListDto[] getTestListDtos() {
        return testListDtos;
    }

    public void setTestListDtos(TestListDto[] testListDtos) {
        this.testListDtos = testListDtos;
    }

    public List<Integer> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Integer> priceList) {
        this.priceList = priceList;
    }

    public Integer[] getPrices() {
        return prices;
    }

    public void setPrices(Integer[] prices) {
        this.prices = prices;
    }
}
