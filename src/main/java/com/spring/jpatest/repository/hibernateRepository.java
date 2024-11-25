package com.spring.jpatest.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface hibernateRepository{

    public void memberSave();
    
    public void memberUpdate(int id);
} 