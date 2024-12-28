package com.spring.jpatest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.jpatest.service.singletonServicetest;

import static org.assertj.core.api.Assertions.assertThat;

public class JpatestApplicationTests {

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

}
