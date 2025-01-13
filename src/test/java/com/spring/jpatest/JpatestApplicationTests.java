package com.spring.jpatest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpatest.entity.springjpaMainEntity;
import com.spring.jpatest.repository.querydslRepository;
import com.spring.jpatest.repository.querydslRepositoryImpl;
import com.spring.jpatest.repository.springjpaRepository;
import com.spring.jpatest.service.singletonServicetest;

import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JpatestApplicationTests {

	private final querydslRepository querydslrepository;
	private final springjpaRepository springjpaRepository;

	@Autowired
	public JpatestApplicationTests(querydslRepository querydslrepository , springjpaRepository springjpaRepository){
		this.querydslrepository = querydslrepository;
		this.springjpaRepository = springjpaRepository;
	}

	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	public void singletonContainer(){

		// 싱글톤 테스트
		singletonServicetest singletonServicetest_1 = singletonServicetest.getInstance();
		singletonServicetest singletonServicetest_2 = singletonServicetest.getInstance();

		System.out.println("singletonServicetest_1 = " + singletonServicetest_1);
        System.out.println("singletonServicetest_2 = " + singletonServicetest_2);
		assertThat(singletonServicetest_1).isSameAs(singletonServicetest_2);
		singletonServicetest_1.call();
        singletonServicetest_2.call();
	}

	@Test
	@DisplayName("호텔로직이 성공적으로 입력되면 성공")
	public void entityManagerAdd(){
		//querydslrepository.insertRoom();
	}

	@Test
	@DisplayName("JPA 1차 캐시 테스트")
	@Transactional
	public void oneCache(){

		System.out.println("1차 캐시에 존재하지 않을경우");
		springjpaRepository.findById(1).get();
		System.out.println("1차 캐시에 존재 할 경우");
        springjpaRepository.findById(1).get();

	}


	@Test
	@DisplayName("JPA 2차 캐시 테스트")
	public void twoCache(){

		springjpaMainEntity entity = springjpaRepository.save(new springjpaMainEntity("test", 30, 3456283));

		springjpaRepository.findById(entity.getSeq()).get();
        springjpaRepository.findById(entity.getSeq()).get();

		springjpaRepository.findById(1).get();

		twoCacheSub(entity);
	}

	private void twoCacheSub(springjpaMainEntity entity){
		springjpaRepository.findById(entity.getSeq()).get();

		springjpaRepository.findById(1).get();
	}
}
