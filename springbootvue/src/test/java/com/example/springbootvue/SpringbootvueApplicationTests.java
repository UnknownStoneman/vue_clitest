package com.example.springbootvue;

import com.example.springbootvue.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootvueApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		System.out.println(userMapper.selectList(null));
	}

}
