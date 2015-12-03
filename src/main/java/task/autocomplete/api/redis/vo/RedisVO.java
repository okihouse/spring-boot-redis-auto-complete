package task.autocomplete.api.redis.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedisVO implements Comparable<RedisVO>{

	private String value;
	private int score;
	
	@Override
	public int compareTo(RedisVO hashVO) {
		return ((Integer)hashVO.getScore()).compareTo(this.score);
	}
}
