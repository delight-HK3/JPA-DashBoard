package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpatest.dto.paramDTO;
import com.spring.jpatest.entity.springjpaMainEntity;

public interface springjpaRepository extends JpaRepository<springjpaMainEntity, Integer>{

    // SQL 방식
    @Query(value = "SELECT * FROM t_springdatajpa_main", nativeQuery = true)
    List<springjpaMainEntity> searchListSql();
    
    // SQL 방식 2
    @Query(value = "SELECT * FROM t_springdatajpa_main where uniquenum = :#{#paramdto.searchNum}", nativeQuery = true)
    springjpaMainEntity searchListSqlone(@Param(value="paramdto")paramDTO paramdto);

    // JPQL 방식
    @Query(value = "SELECT jpa FROM springjpaMainEntity jpa")
    List<springjpaMainEntity> searchListJpql();

    @Query(value = "SELECT jpa FROM springjpaMainEntity jpa where jpa.uniqueNum = :#{#paramdto.searchNum}")
    springjpaMainEntity searchListJpqlone(@Param("paramdto")paramDTO paramdto);

    @Query(value = "update springjpaMainEntity jpa set jpa.name = :#{#paramMember.name} where jpa.uniqueNum = :#{#paramdto.searchNum}")
	@Modifying
	@Transactional
	public int updateJPQL(@Param(value = "paramdto") paramDTO paramdto);
}
