package net.okihouse.autocomplete.key;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import net.okihouse.autocomplete.repository.AutocompleteKeyRepository;

public class AutocompleteKeyServiceImpl implements AutocompleteKeyRepository {

	protected static final String DELIMITER = ":";
	protected static final String PREFIX = "autocomplete" + DELIMITER;

	private StringRedisTemplate stringRedisTemplate;

	public AutocompleteKeyServiceImpl(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@Override
	public String create(final String word, final String identifier) {
		Assert.hasLength(word, "Word cannot be empty or null");

		String trimedWord =  word.trim();
		String firstLetter = getPrefix(trimedWord);

		String generatedKey = generateKey(firstLetter, trimedWord.length());
		
		if(!hasKey(generatedKey, trimedWord, identifier)) {
			stringRedisTemplate.opsForZSet().add(generatedKey, trimedWord + identifier, 1);
			stringRedisTemplate.opsForZSet().add(generatedKey, firstLetter, 0);
			
			for (int index = 1; index < trimedWord.length(); index++) {
				stringRedisTemplate.opsForZSet().add(generatedKey, trimedWord.substring(0, index), 0);
			}
		}
		
		return generatedKey;
	}

	@Override
	public String getKey(String word) {
		String firstLetter = getPrefix(word);
		return generateKeyWithoutLength(firstLetter);
	}

	@Override
	public double incr(final String word, final String identifier) {
		Assert.hasLength(word, "Word cannot be empty or null");
		
		String trimedWord =  word.trim();
		String firstLetter = getPrefix(trimedWord);

		String generatedKey = generateKey(firstLetter, trimedWord.length());
		if(!hasKey(generatedKey, trimedWord, identifier)) return 0;
		
		return stringRedisTemplate.opsForZSet().incrementScore(generatedKey, trimedWord + identifier, 1);
	}

	private String generateKey(final String firstLetter, int length){
		return generateKeyWithoutLength(firstLetter) + length;
	}

	private String generateKeyWithoutLength(final String firstLetter){
		return PREFIX + firstLetter + DELIMITER;
	}

	private boolean hasKey(final String key, final String word, final String identifier){
		Double exist = stringRedisTemplate.opsForZSet().score(key, word.trim() + identifier);
		return exist != null;
	}

	private String getPrefix(final String word){
		return word.substring(0, 1);
	}

}
