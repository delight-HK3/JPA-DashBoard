package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
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
    public Page<boardListDTO> getBoardList(Pageable pageable) {
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
                                    .offset(pageable.getOffset())
                                    .limit(pageable.getPageSize())
                                    .fetch();
        
        JPQLQuery<Board> count = queryFactory.selectFrom(board);

        // getPage(결과목록, 페이지네이션 설정, 조건에 맞는 데이터 총 개수)
        // 성능 최적화를 위해 PageImpl대신 PageableExecutionUtils사용
        // (PageImpl에서 DB 3번 호출할 때 PageableExecutionUtils는 2번 호출)
        return PageableExecutionUtils.getPage(result, pageable, () -> count.fetchCount());
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

        } catch (NullPointerException e) {
            // 유저가 없는 경우 (설마 일어날까 싶지만)
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
