[![Build Status](https://travis-ci.org/okihouse/spring-boot-redis-auto-complete.svg?branch=master)](https://travis-ci.org/okihouse/spring-boot-redis-auto-complete)

<img src="https://raw.githubusercontent.com/okihouse/spring-boot-redis-auto-complete/master/autocomplete.gif" width="250">


# Description
This project supports the `automatic completion` of the word.

Also, you can include points in the word. (like as related contents points for the Instagram)


# How to use?
Add the following dependency in __pom.xml__
```xml
<dependency>
  <groupId>com.github.okihouse</groupId>
  <artifactId>autocomplete</artifactId>
  <version>1.0.1</version>
</dependency>
```

# Basic Examples

```java

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  private AutocompleteKeyRepository keyRepository = new AutocompleteKeyServiceImpl(stringRedisTemplate);
  private AutocompleteRepository autocomplete = new AutocompleteServiceImpl(stringRedisTemplate, keyRepository);
  
  @Test
  public void autocomplete() throws Exception {
    String apple = "apple";

    // step1. clear a "apple"
    autocompleteRepository.clear(apple);

    // step2. Add a "apple"
    autocompleteRepository.add(apple);

    // step3. auto-complete
    List<AutocompleteData> autocompletes = autocompleteRepository.complete("app");

    Assert.assertNotNull(autocompletes);
    Assert.assertTrue(autocompletes.size() == 1);

    AutocompleteData autocompleteData = autocompletes.get(0);

    // Value must be "apple"
    Assert.assertTrue(autocompleteData.getValue().equals(apple));
    Assert.assertTrue(autocompleteData.getScore() == 1);
  }

```


# Requirements
* Java 1.8
* [Spring boot](http://projects.spring.io/spring-boot/) 1.2.8+ (spring-boot-starter-redis)
* [Redis](http://redis.io/) 2.4+
