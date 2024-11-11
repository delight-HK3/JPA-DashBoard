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
    
    public springjpaMainEntity searchId(){
        return springjparepository.findById(1).get();
    }

    public List<springjpaMainEntity> searchAll(){
        return springjparepository.findAll();
    }

    public void save(springjpaMainEntity springjpaMainEntity){
        springjparepository.save(springjpaMainEntity);
    }

    public void saveAll(List<springjpaMainEntity> springjpaMainEntity){
        springjparepository.saveAll(springjpaMainEntity);
    }

    public void delete(springjpaMainEntity springjpaMainEntity){
        springjparepository.delete(springjpaMainEntity);
    }

    public void deleteAll(){
        springjparepository.deleteAll();
    }

    public void deleteById(int id){
        springjparepository.deleteById(id);
    }

    public void deleteAllById(List<Integer> id){
        springjparepository.deleteAllById(id);
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
