package task.autocomplete.api.redis.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import task.autocomplete.api.redis.AutoCompleteConfigure;
import task.autocomplete.api.redis.manager.KeyManager;

@Component(value = "tagKeyManager")
public class TagKeyManager implements KeyManager {

	private static final Logger logger = LoggerFactory.getLogger(TagKeyManager.class);
	
	protected static final String KEY_PREFIX = "tag:";
	protected static final String KEY_DELEMETER = ":";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private AutoCompleteConfigure configure;
	
	@Override
	public String generateKey(final String word) {
		if (validate(word) == false) return "";
		
		String generateKey = getKey(word);
		if (isExistKey(generateKey, word) == false) {
			stringRedisTemplate.opsForZSet().add(generateKey, getPrefix(word), 0);
		}
		return generateKey;
	}
	
	private boolean isExistKey(final String key, final String word){
		Double exist = stringRedisTemplate.opsForZSet().score(key, word.trim() + configure.getDelimiter());
		return exist != null;
	}
	
	protected boolean validate(String word){
		if (word == null || word.trim().isEmpty()) {
			logger.warn("Request word is invalid. word={}", word);
			return false;
		}
		return true;
	}
	
	private String getKey(final String word){
		String generateKey = KEY_PREFIX + getPrefix(word) + KEY_DELEMETER + word.trim().length();
		return generateKey;
	}
	
	private String getPrefix(final String word){
		String prefix = word.trim().substring(0, 1);
		return prefix;
	}

	@Override
	public boolean existKey(String word) {
		if (validate(word) == false) return false;
		return isExistKey(getKey(word), word);
	}

}
