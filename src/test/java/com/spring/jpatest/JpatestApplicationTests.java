package com.spring.jpatest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.spring.jpatest.config.SecurityConfiguration;
import com.spring.jpatest.config.querydslConfig;
import com.spring.jpatest.domain.User;
import com.spring.jpatest.dto.userDTOTest;
import com.spring.jpatest.repository.userRepository;

@SpringBootTest
@DataJpaTest
@Import(querydslConfig.class)
public class JpatestApplicationTests {
	
	@Autowired
	private userRepository userRepository;

	@Test
	@DisplayName("유저정보가 정상일 경우 등록 테스트")
	public void userOk(){

		//System.out.println("test");

		userDTOTest userdto = new userDTOTest("test1","admin","tester1");

		User user = User.builder()
                        .nickName(userdto.getNickName())
                        .userId(userdto.getUserId())
                        .password(userdto.getPassword())
                        .build();
        
        //userRepository.save(user);

	}


	@Test
	@DisplayName("유저정보가 없는 경우 등록 테스트")
	public void userfalse(){
		
		// 데이터가 없는 경우에는 java.lang.IllegalStateException 발생
		//userDTOTest userdto = new userDTOTest("","","");

	}

}
