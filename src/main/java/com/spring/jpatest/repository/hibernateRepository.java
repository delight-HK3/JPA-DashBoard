package com.spring.jpatest.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface hibernateRepository{

    public void memberSave();
    
    public void memberUpdate(int id);

    public void memeberDelete(int id);

    public void memberGetReference(int id);

    public void createsql();

    public void createjpql();

    public void createnamedsql();
} 