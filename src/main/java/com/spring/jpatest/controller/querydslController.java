package com.spring.jpatest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.Tuple;
import com.spring.jpatest.dto.memresultDTO;
import com.spring.jpatest.dto.paramDTO;
import com.spring.jpatest.dto.resultDTO;
import com.spring.jpatest.entity.Member;
import com.spring.jpatest.service.querydslService;

@RestController
public class querydslController {
    
    private final querydslService querydslservice;

    querydslController(querydslService querydslservice){
        this.querydslservice = querydslservice;
    }

    // 원래 entity이 아닌 dto로 리턴해야한다.
    @RequestMapping(value="/dsl/all", method=RequestMethod.GET)
    public List<memresultDTO> dslAll(paramDTO paramdto) {
        return querydslservice.searchAll();
    }

    @RequestMapping(value="/dsl/one", method=RequestMethod.GET)
    public Member dslOne(paramDTO paramdto) {
        return querydslservice.searchOne(1);
    }

    @RequestMapping(value="/dsl/add", method=RequestMethod.GET)
    public void dslAdd() {
        long add = querydslservice.insert();
        System.out.println(add); // 추가한 카운트 출력
    }

    @RequestMapping(value="/dsl/updt", method=RequestMethod.GET)
    public void dslUpdt() {
        long updt = querydslservice.update();
        System.out.println(updt); // 수정한 Row개수 출력
    }

    @RequestMapping(value="/dsl/del", method=RequestMethod.GET)
    public void dslDel() {
        long del = querydslservice.delete();
        System.out.println(del); // 삭제한 Row개수 출력
    }
}
