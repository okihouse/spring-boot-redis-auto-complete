package task.autocomplete.api.redis.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import task.autocomplete.api.redis.manager.KeyManager;

@Component(value = "tagKeyManager")
public class TagKeyManager implements KeyManager {

	private static final Logger logger = LoggerFactory.getLogger(TagKeyManager.class);
	
	protected static final String KEY_PREFIX = "tag:";
	protected static final String KEY_DELEMETER = ":";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public String generateKey(final String word) {
		if (validate(word) == false) return "";
		
		String prefix = word.substring(0, 1), generateKey = KEY_PREFIX + prefix + KEY_DELEMETER + word.trim().length();
		if (isExistKey(generateKey, word) == false) {
			stringRedisTemplate.opsForZSet().add(generateKey, prefix, 0);
		}
		return generateKey;
	}
	
	private boolean isExistKey(final String key, final String word){
		Long exist = stringRedisTemplate.opsForZSet().rank(key, word);
		return exist != null && exist != 0;
	}
	
	protected boolean validate(String word){
		if (word == null || word.isEmpty()) {
			logger.warn("Request word is invalid. word={}", word);
			return false;
		}
		return true;
	}

	@Override
	public boolean existKey(String word) {
		if (validate(word) == false) return false;
		return isExistKey(generateKey(word), word);
	}

}
