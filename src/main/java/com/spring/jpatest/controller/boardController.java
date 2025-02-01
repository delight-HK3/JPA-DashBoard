package com.spring.jpatest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jpatest.dto.board.boardDetailDTO;
import com.spring.jpatest.dto.board.boardListDTO;
import com.spring.jpatest.dto.board.boardSaveDTO;
import com.spring.jpatest.service.boardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class boardController {

    private final boardService boardservice;

    public boardController(boardService boardservice){
        this.boardservice = boardservice;
    }

    /**
     * board - 메인 게시판 로딩
     * 
     * @param mav
     * @return
     */
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public ModelAndView boardMain(ModelAndView mav) {

        List<boardListDTO> boardList = boardservice.getBoardList();

        mav.addObject("boardList", boardList);
        mav.setViewName("board/boardListPage");

        return mav;
    }

    /**
     * board - 메인 게시글 상세보기, 조회수 1 증가
     * 
     * @param mav
     * @param boardCd
     * @return
     */
    @RequestMapping(value="/board/detail", method=RequestMethod.GET)
    public ModelAndView boardDetailPage(ModelAndView mav, @RequestParam int boardCd) {

        boardDetailDTO boardDetail = boardservice.getBoardDetail(boardCd);
        
        mav.addObject("boardDetail", boardDetail);
        mav.setViewName("board/boardDetailPage");

        return mav;
    }

    /**
     * board - 게시글 입력 페이지 로딩
     * 
     * @param mav
     * @return
     */
    @RequestMapping(value="/board/add", method=RequestMethod.GET)
    public ModelAndView boardAddPage(boardSaveDTO boardSavedto, ModelAndView mav) {

        mav.addObject("boarddto", boardSavedto);
        mav.setViewName("board/boardAddPage");

        return mav;
    }

    /**
     * board - 게시글 내용 등록
     * 
     * @param boardSavedto
     * @param request
     * @return
     */
    @RequestMapping(value="/board/save", method=RequestMethod.POST)
    public String boardSave(@ModelAttribute("boarddto") boardSaveDTO boardSavedto, HttpServletRequest request) {

        HttpSession session = request.getSession();
        boardSavedto.setUseruuid((UUID) session.getAttribute("useruuid"));
        
        boardservice.boardSave(boardSavedto);

        return "redirect:/";
    }
}
