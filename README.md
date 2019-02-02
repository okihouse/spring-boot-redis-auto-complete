[![Build Status](https://travis-ci.org/okihouse/spring-boot-redis-auto-complete.svg?branch=master)](https://travis-ci.org/okihouse/spring-boot-redis-auto-complete)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.okihouse/autocomplete/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.okihouse/autocomplete)

<img src="https://raw.githubusercontent.com/okihouse/spring-boot-redis-auto-complete/master/autocomplete.gif" width="250">

# Description
This project supports the `automatic completion` of the word.

Also, you can include points in the word. (like as related contents points for the Instagram)


# Benchmark
Each step was tested 10 times. (10k words, 100k words, 100k fixed length words)

You can checkout the [Autocomplete benchmark project for details](https://github.com/okihouse/spring-boot-redis-auto-complete/tree/master/spring-boot-redis-autocomplete-benchmark) and review/run the benchmarks yourself.

The average response rate is as follows :

<img src="https://raw.githubusercontent.com/okihouse/spring-boot-redis-auto-complete/master/autocomplete_benchmark.png" width="500">

<sup>
<sup>1</sup> Environments : Local(macbook), 2.5 GHz Intel Core i7, 16GB 1600 MHz DDR3 <br/>
<sup>2</sup> 10k words: https://github.com/first20hours/google-10000-english <br/>
<sup>3</sup> 100k words: https://gist.github.com/h3xx/1976236 <br/>
</sup>


# How to use?
Add the following dependency in __pom.xml__ (Java 8 maven artifact)
```xml
<dependency>
  <groupId>com.github.okihouse</groupId>
  <artifactId>autocomplete</artifactId>
  <version>1.0.1</version>
</dependency>
```


# Bean Configuration

```java

@Autowired
private StringRedisTemplate stringRedisTemplate;

@Bean(name = {"autocompleteKeyRepository", "keyRepository"})
public AutocompleteKeyRepository keyRepository() {
	AutocompleteKeyRepository keyRepository = new AutocompleteKeyServiceImpl(stringRedisTemplate);
	return keyRepository;
}

@Bean(name = {"autocompleteRepository"})
public AutocompleteRepository autocompleteRepository(AutocompleteKeyRepository autocompleteKeyRepository) {
	AutocompleteRepository autocompleteRepository = new AutocompleteServiceImpl(stringRedisTemplate, autocompleteKeyRepository);
	return autocompleteRepository;
}

```


# Examples

```java

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

```


# Requirements
This project depend on `spring-boot` and `spring-boot-redis` (Prerequisites)

* Java 1.8
* [Spring boot](http://projects.spring.io/spring-boot/) 1.2.8+ (spring-boot-starter-redis)
* [Redis](http://redis.io/) 2.4+

# Note
* There are many places to repair. 
* All contributions are welcome.
* Please use the [GitHub issue tracker](https://github.com/okihouse/spring-boot-redis-auto-complete/issues) if you have any ideas or bugs to report.


