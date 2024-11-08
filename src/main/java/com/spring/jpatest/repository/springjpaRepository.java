package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.jpatest.entity.springjpaMainEntity;

public interface springjpaRepository extends JpaRepository<springjpaMainEntity, Integer>{

    @Query(value = "SELECT * FROM t_springdatajpa_main", nativeQuery = true)
    List<springjpaMainEntity> searchList();
    
    
}
