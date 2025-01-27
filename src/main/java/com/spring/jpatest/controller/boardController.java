package com.spring.jpatest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jpatest.dto.board.boardDTO;
import com.spring.jpatest.service.boardService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class boardController {

    private final boardService boardservice;

    public boardController(boardService boardservice){
        this.boardservice = boardservice;
    }

    /**
     * 게시판 로딩
     * 
     * @param mav
     * @return
     */
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public ModelAndView requestMethodName(ModelAndView mav) {

        List<boardDTO> boardList = boardservice.getBoardList();

        mav.addObject("boardList", boardList);
        mav.setViewName("content/index");

        return mav;
    }



}
