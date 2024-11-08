package com.spring.jpatest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpatest.entity.springjpaMainEntity;
import com.spring.jpatest.repository.springjpaRepository;

@Service
public class springjpaService {
    
    private final springjpaRepository springjparepository;
    
    springjpaService(springjpaRepository springjparepository){
        this.springjparepository = springjparepository;
    }
    
    public List<springjpaMainEntity> searchList(){
        return springjparepository.searchList();
    } 
}
