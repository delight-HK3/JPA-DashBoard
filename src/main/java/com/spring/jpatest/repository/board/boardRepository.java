package com.spring.jpatest.repository.board;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jpatest.domain.Board;
import com.spring.jpatest.domain.User;


@Repository
public interface boardRepository extends JpaRepository<Board, Integer>{
    Optional<Board> findByUserBoard(User user, Board board);
} 
