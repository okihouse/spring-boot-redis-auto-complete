package net.okihouse.autocomplete.repository;

public interface AutocompleteKeyRepository {

	String create(String word, String identifier);

	double incr(String word, String identifier);

	String getKey(String firstLetter);

}
