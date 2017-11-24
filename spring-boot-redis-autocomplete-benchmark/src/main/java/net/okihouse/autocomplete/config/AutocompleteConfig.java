package net.okihouse.autocomplete.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import net.okihouse.autocomplete.implement.AutocompleteServiceImpl;
import net.okihouse.autocomplete.key.AutocompleteCustomKeyImpl;
import net.okihouse.autocomplete.repository.AutocompleteKeyRepository;
import net.okihouse.autocomplete.repository.AutocompleteRepository;

@Configuration
public class AutocompleteConfig {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Bean(name = {"autocompleteKeyRepository", "keyRepository"})
	public AutocompleteKeyRepository keyRepository() {
		// auto-complete custom key implements for test
		AutocompleteKeyRepository keyRepository = new AutocompleteCustomKeyImpl();
		return keyRepository;
	}

	@Bean(name = {"autocompleteRepository"})
	public AutocompleteRepository autocompleteRepository(AutocompleteKeyRepository autocompleteKeyRepository) {
		AutocompleteRepository autocompleteRepository = new AutocompleteServiceImpl(stringRedisTemplate, autocompleteKeyRepository);
		return autocompleteRepository;
	}

}
