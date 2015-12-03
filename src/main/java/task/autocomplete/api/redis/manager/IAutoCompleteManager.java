package task.autocomplete.api.redis.manager;

import java.util.List;

import task.autocomplete.api.redis.vo.RedisVO;

public interface IAutoCompleteManager {

	public List<RedisVO> complete(final String word);
	
	public List<RedisVO> complete(final String word, final double min, final double max);
	
	public Boolean addWord(final String word, final int score);
	
	public Double incr(final String word);
	
	public void clear(final String key);
}
