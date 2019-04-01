package com.xiaoxuan.dto;

public class TestListDto {
    private String address;

    private int code;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static TestListDto init(String address,int code){
        TestListDto testListDto = new TestListDto();
        testListDto.setAddress(address);
        testListDto.setCode(code);
        return testListDto;
    }
}
