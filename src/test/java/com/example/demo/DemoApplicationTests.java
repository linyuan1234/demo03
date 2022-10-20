package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
@Slf4j
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;
	@Test
	void contextLoads() {
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
		for (Map<String,Object> map:maps){
			System.out.println(map);
		}

		log.info("数据源类型:{}",dataSource.getClass());
	}

}
