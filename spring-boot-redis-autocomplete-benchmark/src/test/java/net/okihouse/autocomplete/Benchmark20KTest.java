package net.okihouse.autocomplete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.Resource;

import net.okihouse.autocomplete.base.BaseTest;

public class Benchmark20KTest extends BaseTest {

	@Before
	public void before() throws IOException{
		// clear all data
		redisConnectionFactory.getConnection().flushAll();

		long startTime = System.currentTimeMillis();
		Resource resource = resourceLoader.getResource("classpath:20k.txt"); // Load 20k word file
		BufferedReader in = new BufferedReader(new InputStreamReader(resource.getInputStream()));

		String word = null;
		while((word = in.readLine()) != null) {
			if(word.isEmpty()) continue;
			autocompleteRepository.add(word);
		}

		long elapsed = System.currentTimeMillis() - startTime;
		Long totalCount = redisConnectionFactory.getConnection().dbSize(); // get saved key size
		logger.info("Add 20k words on redis. elapsed={}ms, totalCount={}", elapsed, totalCount);
	}

	@Test
	public void test() throws Exception {
		int loopCount = 10;
		long elapsed = 0;
		for (int i = 0; i < loopCount; i++) {
			elapsed = elapsed + autocomplete();
		}
		logger.info("Test autocomplete 20k words average elapsed={}ms", ((float)elapsed / loopCount));
	}

}
