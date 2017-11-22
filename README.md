[![Build Status](https://travis-ci.org/okihouse/spring-boot-redis-auto-complete.svg?branch=master)](https://travis-ci.org/okihouse/spring-boot-redis-auto-complete)

# Description
This project supports the `automatic completion` of the word.

Also, you can include points in the word. (like as related contents points for the Instagram)

이 프로젝트는 단어 자동완성 기능을 제공합니다. 

또한, 단어마다 점수를 줄 수 있습니다. (마치 인스타그램의 태그 검색의 연관된 게시글 수 표시와 비슷합니다)

<img src="https://raw.githubusercontent.com/okihouse/spring-boot-redis-auto-complete/master/autocomplete.gif" width="250">


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
This project depend on `spring-boot` and `spring-boot-redis`

* Java 1.8
* [Spring boot](http://projects.spring.io/spring-boot/) 1.2.8+ (spring-boot-starter-redis)
* [Redis](http://redis.io/) 2.4+



