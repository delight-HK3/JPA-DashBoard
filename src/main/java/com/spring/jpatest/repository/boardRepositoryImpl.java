package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.jpatest.dto.boardDTO;
import static com.spring.jpatest.domain.QBoard.board;
import static com.spring.jpatest.domain.QUser.user;


@Repository
public class boardRepositoryImpl implements boardRepository{
    
    private JPAQueryFactory queryFactory;

    public boardRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    @Override
    public List<boardDTO> getBoardList() {
        List<boardDTO> result = queryFactory
                                    .select(Projections.constructor(boardDTO.class,
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
    
}
