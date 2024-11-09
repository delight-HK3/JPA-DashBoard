package com.spring.jpatest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpatest.dto.resultDTO;
import com.spring.jpatest.entity.springjpaMainEntity;
import com.spring.jpatest.repository.springjpaRepository;

@Service
public class springjpaService {
    
    private final springjpaRepository springjparepository;
    
    springjpaService(springjpaRepository springjparepository){
        this.springjparepository = springjparepository;
    }
    
    public List<resultDTO> searchList(){
        List<resultDTO> dtoList = new ArrayList<>();
        List<springjpaMainEntity> resultList = springjparepository.searchListJpql();

        for(int i = 0; i < resultList.size(); i++){
            resultDTO dto = resultDTO.entitytoDto(resultList.get(i));
            dtoList.add(dto);
        }

        return dtoList;
    } 
}
