package net.okihouse.autocomplete.repository;

import java.util.List;

import net.okihouse.autocomplete.vo.AutocompleteData;

public interface AutocompleteRepository {

	String DEFAULT_DELIMITER = "§";

	List<AutocompleteData> complete(final String word);

	List<AutocompleteData> complete(final String word, final double min, final double max, final int offset);

	void add(final String word);

	double incr(final String word);

	void clear(final String key);

}
