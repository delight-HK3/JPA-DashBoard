package com.spring.jpatest.dto;

import lombok.Getter;

@Getter
public class memresultDTO {
    private String name;
    private int age;

    public memresultDTO(String name, int age){
        this.name = name;
        this.age = age;
    }
}
