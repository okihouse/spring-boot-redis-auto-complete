package task.autocomplete.api.redis.tag;

import org.springframework.stereotype.Service;

@Service(value = "tagKeyManagerService")
public class TagKeyManagerService extends TagKeyManager {

	public String getKeyWithoutLength(final String word){
		if (validate(word) == false) return "";
		return KEY_PREFIX + word.substring(0, 1) + KEY_DELEMETER;
	}
}
