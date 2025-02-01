package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.jpatest.domain.Board;
import com.spring.jpatest.domain.User;
import com.spring.jpatest.dto.board.boardDetailDTO;
import com.spring.jpatest.dto.board.boardListDTO;
import com.spring.jpatest.dto.board.boardSaveDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static com.spring.jpatest.domain.QBoard.board;
import static com.spring.jpatest.domain.QUser.user;


@Repository
public class boardRepositoryImpl implements boardRepository{
    
    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager em;

    public boardRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    @Override
    public List<boardListDTO> getBoardList() {
        List<boardListDTO> result = queryFactory
                                    .select(Projections.constructor(boardListDTO.class,
                                        board.seq
                                        , board.boardTitle
                                        , board.viewCnt
                                        , board.likeCnt
                                        , board.instDate
                                        , user.nickName
                                    ))
                                    .from(board)
                                    .join(board.user, user)
                                    .fetch();
        
        return result;
    }

    @Override
    public boardDetailDTO getBoardDetail(int boardCd) {

        boardDetailDTO result = queryFactory
                                .select(Projections.constructor(boardDetailDTO.class, 
                                    user.nickName
                                    , board.boardTitle
                                    , board.boardSubject
                                    , board.viewCnt
                                    , board.likeCnt
                                    , board.instDate
                                ))
                                .from(board)
                                .where(board.seq.eq(boardCd))
                                .join(board.user, user)
                                .fetchOne();

        return result;
    }

    @Override
    @Transactional
    public void boardCntUp(int boardCd) {
        
        try{
            Board detail = queryFactory.selectFrom(board)
                                        .where(board.seq.eq(boardCd))
                                        .fetchOne();
            
            // 동시성 문제 고민
            detail.setViewCnt(detail.getViewCnt() + 1);

        } catch (NullPointerException e) {
            //e.printStackTrace();
            throw new NullPointerException();
            //em.getTransaction().rollback(); // 롤백
        } 

        em.close(); // 사용한 entityManager 닫기
    }

    @Override
    @Transactional
    public void boardSave(boardSaveDTO boardSavedto) {

        try{
            User userOne = queryFactory.selectFrom(user)
                        .where(user.useruuid.eq(boardSavedto.getUseruuid()))
                        .fetchOne();

            Board board = Board.builder()
                            .user(userOne)
                            .boardTitle(boardSavedto.getBoardTitle())
                            .boardSubject(boardSavedto.getBoardSubject())
                            .instDate(boardSavedto.getInstDate())
                            .build();

            em.persist(board);

        } catch (Exception e) {
            e.printStackTrace();
            //em.getTransaction().rollback(); // 롤백
        } 
        
        em.close(); // 사용한 entityManager 닫기
    }

    @Override
    public void boardDelete() {
        
        // 특정 ID 게시글 삭제

        throw new UnsupportedOperationException("Unimplemented method 'boardDelete'");
    }
    
}
