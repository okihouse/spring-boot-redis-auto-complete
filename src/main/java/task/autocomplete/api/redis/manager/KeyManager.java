package task.autocomplete.api.redis.manager;

public interface KeyManager {

	public String generateKey(final String word);
	
	public boolean existKey(final String word);
}
