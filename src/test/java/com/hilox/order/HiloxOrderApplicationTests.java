package com.hilox.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试类
 * Created by Hilox on 2018/12/6 0006.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class HiloxOrderApplicationTests {

	@Test
	public void testLogger() {
		log.info("info");
		log.error("error");
		log.debug("debug");
	}

}
