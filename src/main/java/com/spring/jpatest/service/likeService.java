package com.spring.jpatest.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.spring.jpatest.domain.Board;
import com.spring.jpatest.domain.Likes;
import com.spring.jpatest.domain.User;
import com.spring.jpatest.dto.Like.likeRequestDTO;
import com.spring.jpatest.exception.exceptionEnum;
import com.spring.jpatest.exception.custom.AlreadyLikeException;
import com.spring.jpatest.exception.custom.NoBoardDataException;
import com.spring.jpatest.exception.custom.NoUserDataException;
import com.spring.jpatest.exception.custom.NofindLikeException;
import com.spring.jpatest.repository.board.boardRepository;
import com.spring.jpatest.repository.likes.likeRepository;
import com.spring.jpatest.repository.user.userRepository;

@Service
public class likeService {
    
    private final boardRepository boardRepository;
    private final userRepository userRepository;
    private final likeRepository likeRepository;

    public likeService(boardRepository boardRepository, userRepository userRepository, likeRepository likeRepository){
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    /**
     * 좋아요 클릭
     * 
     * @param likeRequestdto
     */
    public void likeInsert(likeRequestDTO likeRequestdto){

        User user = userRepository.findById(likeRequestdto.getUserId())
                        .orElseThrow(() -> new NoUserDataException(likeRequestdto.getUserId())) ;

        Board board = boardRepository.findById(likeRequestdto.getBoardId())
                        .orElseThrow(() -> new NoBoardDataException(likeRequestdto.getBoardId()));

        // 이미 좋아요를 클릭한 경우
        if(likeRepository.findByUserBoard(user, board).isPresent()){
            throw new AlreadyLikeException(exceptionEnum.ALREADY_LIKE);
        }
        
        Likes likes = new Likes(user, board);

        likeRepository.save(likes);
        boardRepository.updateLikeUpdate(board, true);
    }

    /**
     * 좋아요 취소
     * 
     * @param likeRequestdto
     */
    public void likedelete(likeRequestDTO likeRequestdto){

        User user = userRepository.findById(likeRequestdto.getUserId())
                        .orElseThrow(() -> new NoUserDataException(likeRequestdto.getUserId())) ;

        Board board = boardRepository.findById(likeRequestdto.getBoardId())
                        .orElseThrow(() -> new NoBoardDataException(likeRequestdto.getBoardId()));
        
        Likes likes = likeRepository.findByUserBoard(user, board)
                        .orElseThrow(() -> new NofindLikeException(exceptionEnum.NO_FIND_LIKE));           
        
        likeRepository.delete(likes);
        boardRepository.updateLikeUpdate(board, false);
    }
}
