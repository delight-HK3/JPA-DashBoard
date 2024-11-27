package com.spring.jpatest.service;

import org.springframework.stereotype.Service;

import com.spring.jpatest.repository.hibernateRepository;

@Service
public class hibernateService {
    
    private final hibernateRepository hibernaterepository;

    hibernateService(hibernateRepository hibernaterepository){
        this.hibernaterepository = hibernaterepository;
    }

    public void save(){
        hibernaterepository.memberSave();
    }

    public void update(int id){
        hibernaterepository.memberUpdate(id);
    }

    public void getReference(int id){
        hibernaterepository.memberGetReference(id);
    }

    public void getCreateJpql(){
        hibernaterepository.createjpql();
    }

    public void getCreatenamedsql(){
        hibernaterepository.createnamedsql();
    }

    public void getCreatesql(){
        hibernaterepository.createsql();
    }
}
