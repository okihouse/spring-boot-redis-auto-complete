package net.okihouse.autocomplete.key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import net.okihouse.autocomplete.repository.AutocompleteKeyRepository;

@Service
public class AutocompleteCustomKeyImpl implements AutocompleteKeyRepository {

	protected static final String DELIMITER = ":";
	protected static final String PREFIX = "autocomplete" + DELIMITER;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public String create(String word, String identifier) {
		Assert.hasLength(word, "Word cannot be empty or null");

		String trimedWord =  word.trim();
		String firstLetter = getPrefix(trimedWord);
		String generatedKey = generateKey(firstLetter, trimedWord.length());

		stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
				stringRedisConn.zAdd(generatedKey, 1, trimedWord + identifier);
				stringRedisConn.zAdd(generatedKey, 1, firstLetter);
				for (int index = 1; index < trimedWord.length(); index++) {
					String sliceWord = trimedWord.substring(0, index - 1);
					stringRedisConn.zAdd(generatedKey, 0, sliceWord);
				}
				return null;
			}
		});
		return generatedKey;
	}

	@Override
	public double incr(String word, String identifier) {
		Assert.hasLength(word, "Word cannot be empty or null");
		String trimedWord =  word.trim();
		String firstLetter = getPrefix(trimedWord);

		String generatedKey = generateKey(firstLetter, trimedWord.length());
		if(!hasKey(generatedKey, trimedWord, identifier)) return 0;
		return stringRedisTemplate.opsForZSet().incrementScore(generatedKey, trimedWord + identifier, 1);
	}

	@Override
	public String getKey(String word) {
		String firstLetter = getPrefix(word);
		return generateKeyWithoutLength(firstLetter);
	}

	private boolean hasKey(final String key, final String word, final String identifier){
		Double exist = stringRedisTemplate.opsForZSet().score(key, word.trim() + identifier);
		return exist != null;
	}

	private String generateKey(final String firstLetter, int length){
		return generateKeyWithoutLength(firstLetter) + length;
	}

	private String generateKeyWithoutLength(final String firstLetter){
		return PREFIX + firstLetter + DELIMITER;
	}

	private String getPrefix(final String word){
		return word.substring(0, 1);
	}

}
