package net.okihouse.autocomplete;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import net.okihouse.autocomplete.base.BaseTest;

public class BenchmarkFixedLength100KTest extends BaseTest {

	@Before
	public void before() throws IOException{
		// clear all data
		redisConnectionFactory.getConnection().flushAll();

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) { // 100k
			String word = RandomStringUtils.randomAlphabetic(15); // fixed word length(15)
			autocompleteRepository.add(word);
		}

		long elapsed = System.currentTimeMillis() - startTime;
		Long totalCount = redisConnectionFactory.getConnection().dbSize(); // get saved key size
		logger.info("Add random 100k words on redis. elapsed={}ms, totalCount={}", elapsed, totalCount);
	}

	@Test
	public void test_100k_words() throws Exception {
		int loopCount = 10;
		long elapsed = 0;
		for (int i = 0; i < loopCount; i++) {
			elapsed = elapsed + autocomplete();
		}
		logger.info("Test autocomplete random 100k words average elapsed={}ms", ((float)elapsed / loopCount));
	}

}
