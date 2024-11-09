package com.spring.jpatest.dto;

import com.spring.jpatest.entity.springjpaMainEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class resultDTO {
    
    private String name;
    private int age;
    private int uniqueNum;

    // entity to dto
    public static resultDTO entitytoDto(springjpaMainEntity entity){
        return resultDTO.builder()
                        .age(entity.getAge())
                        .name(entity.getName())
                        .uniqueNum(entity.getUniqueNum())
                        .build();
    }

}
