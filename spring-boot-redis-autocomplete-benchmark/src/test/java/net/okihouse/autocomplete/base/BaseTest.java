package net.okihouse.autocomplete.base;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.okihouse.autocomplete.AutocompleteApplication;
import net.okihouse.autocomplete.repository.AutocompleteRepository;
import net.okihouse.autocomplete.vo.AutocompleteData;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AutocompleteApplication.class)
@WebAppConfiguration
public class BaseTest {

	protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	@Autowired
	protected RedisConnectionFactory redisConnectionFactory;

	@Autowired
	protected StringRedisTemplate stringRedisTemplate;

	@Autowired
	protected AutocompleteRepository autocompleteRepository;

	@Autowired
	protected ResourceLoader resourceLoader;

	protected long autocomplete() {
		String word = RandomStringUtils.randomAlphabetic(1);
		long startTime = System.currentTimeMillis();
		List<AutocompleteData> autocompleteDatas = autocompleteRepository.complete(word);
		String firstWord = "";
		if(!autocompleteDatas.isEmpty()) firstWord = autocompleteDatas.get(0).getValue();

		long elapsed = System.currentTimeMillis() - startTime;
		logger.info("prefix={}, elapsed={}ms, size={}, firstWord={}", word, elapsed, autocompleteDatas.size(), firstWord);
		return elapsed;
	}

}
