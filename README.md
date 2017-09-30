[![Build Status](https://travis-ci.org/okihouse/spring-boot-redis-auto-complete.svg?branch=master)](https://travis-ci.org/okihouse/spring-boot-redis-auto-complete)

<img src="https://raw.githubusercontent.com/okihouse/spring-boot-redis-auto-complete/master/autocomplete.gif" width="250">


# Description
This project offers the `Autocomplete` function for words that start with Korean, English, and numbers. (for UTF-8)

The difference from regular auto complete function is that this offers certain scores for each words.

It is quite useful. For example, It is able to show a number of related contents that includes the word, (just like the instagram search function).

# Requirements
* Java 1.6+ (this project is set up with version 1.8)
* [Spring boot](http://projects.spring.io/spring-boot/) 1.2.8+ (spring-boot-starter-redis)
* [Redis](http://redis.io/) 2.4+

# Usage & Run
1. Install redis in your server or cloud server(e.g aws, azure..)
1. Clone this package.
1. Update redis host and port in `application.yml`.
1. Just run this application(STS, eclipse, intelliJ or maven).

## Sample API

- **`GET`** **Autocomplete**
> /api/{word}  

- **`PUT`** **Add word**
> /api/add/{word} 

- **`POST`** **Raises a score of the word.**
> /api/{word}  
