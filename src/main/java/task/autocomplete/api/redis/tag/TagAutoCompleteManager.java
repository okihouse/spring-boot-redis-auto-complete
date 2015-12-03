package task.autocomplete.api.redis.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import task.autocomplete.api.redis.AutoCompleteConfigure;
import task.autocomplete.api.redis.manager.IAutoCompleteManager;
import task.autocomplete.api.redis.manager.KeyManager;
import task.autocomplete.api.redis.vo.RedisVO;

@Component(value = "tagAutoCompleteManager")
public class TagAutoCompleteManager implements IAutoCompleteManager {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private KeyManager tagKeyManagerService;
	
	@Autowired
	private AutoCompleteConfigure configure;
	
	@Override
	public List<RedisVO> complete(final String prefix) {
		return complete(prefix, configure.getMin(), configure.getMax());
	}
	
	@Override
	public List<RedisVO> complete(final String word, final double min, final double max) {
		List<RedisVO> results = new ArrayList<RedisVO>();
		int wordLength = word.trim().length();
		if (word == null || wordLength == 0) return results;
		
		TagKeyManagerService service = (TagKeyManagerService) tagKeyManagerService;
		for (int i = wordLength; i < 30; i++) {
			if (results.size() == configure.getCount()) break; 
			
			String rewriteKey = service.getKeyWithoutLength(word.trim()) + i;
			Set<TypedTuple<String>> rangeResultsWithScore = stringRedisTemplate
					.opsForZSet()
					.reverseRangeByScoreWithScores(rewriteKey, min, max, 0, configure.getCount());
			if (rangeResultsWithScore.isEmpty()) continue;
			
			for (TypedTuple<String> typedTuple : rangeResultsWithScore) {
				if (results.size() == configure.getCount()) break; 
				
				String value = typedTuple.getValue();
				int minLength = Math.min(value.length(), wordLength);
				if (value.endsWith(configure.getDelemeter()) && value.startsWith(word.trim().substring(0, minLength))) {
					results.add(new RedisVO(value.replace(configure.getDelemeter(), ""), typedTuple.getScore().intValue()));
				} 
			}
		}
		Collections.sort(results);
		return results;
	}

	@Override
	public Boolean addWord(String word, final int score) {
		String key = tagKeyManagerService.generateKey(word);
		if (key == null) return false;
		
		if (tagKeyManagerService.existKey(word) == false) {
			stringRedisTemplate.opsForZSet().add(key, word.trim() + configure.getDelemeter(), score);
			for (int index = 1; index < word.length(); index++) {
				stringRedisTemplate.opsForZSet().add(key, word.trim().substring(0, index - 1), 0);
			}
		}
		return true;
	}

	@Override
	public Double incr(String word) {
		String key = tagKeyManagerService.generateKey(word);
		if (key == null) return 0.0;
		
		return stringRedisTemplate.opsForZSet().incrementScore(key, word.trim() + configure.getDelemeter(), 1);
	}

	@Override
	public void clear(String key) {
		stringRedisTemplate.delete(key);
	}

}
