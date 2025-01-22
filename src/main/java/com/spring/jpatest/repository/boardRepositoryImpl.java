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

    @Override
    public void boardSave() {
        // TODO Auto-generated method stub

        // 게시글 1개 조회 
        // 결과 없으면 저장 / 있으면 수정

        throw new UnsupportedOperationException("Unimplemented method 'boardSave'");
    }

    @Override
    public void boardDelete() {
        
        // 특정 ID 게시글 삭제

        throw new UnsupportedOperationException("Unimplemented method 'boardDelete'");
    }
    
}
