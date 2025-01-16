package com.spring.jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jpatest.domain.Board;

@Repository
public interface boardRepository extends JpaRepository<Board, Integer>{

    
} 