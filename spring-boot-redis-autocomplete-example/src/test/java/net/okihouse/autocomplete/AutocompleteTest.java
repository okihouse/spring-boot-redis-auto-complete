package net.okihouse.autocomplete;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.okihouse.autocomplete.repository.AutocompleteRepository;
import net.okihouse.autocomplete.vo.AutocompleteData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AutocompleteApplication.class)
@WebAppConfiguration
public class AutocompleteTest {

	@Autowired
	private AutocompleteRepository autocompleteRepository;

	@Test
	public void autocomplete() throws Exception {
		String apple = "apple";

		// step1. clear a "apple"
		autocompleteRepository.clear(apple);

		// step2. Add a "apple"
		autocompleteRepository.add(apple);

		// step3. Get auto-complete words with prefix "a"
		List<AutocompleteData> autocompletes = autocompleteRepository.complete("a");

		Assert.assertNotNull(autocompletes);
		Assert.assertTrue(autocompletes.size() == 1);

		AutocompleteData autocompleteData = autocompletes.get(0);

		Assert.assertTrue(autocompleteData.getValue().equals(apple));
		Assert.assertTrue(autocompleteData.getScore() == 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void error() throws Exception {
		autocompleteRepository.complete(null);
	}

}
