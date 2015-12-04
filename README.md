[![Build Status](https://travis-ci.org/okihouse/spring_boot_redis_auto_complete.svg?branch=master)](https://travis-ci.org/okihouse/spring_boot_redis_auto_complete)

# Description
해당 프로젝트는 한글, 영어, 숫자로 시작하는 단어의 자동완성기능을 구현하고 있습니다.

일반적인 자동완성기능과 다른게 각 단어별 점수를 포함하고 있습니다. 

해당 기능은 유용하게 사용할 수 있습니다. ( 예를들어 인스타그램 검색기능의 연관된 게시글 수)

또한, 프로젝트를 그대로 사용하지 마시고 `IAutoCompleteManager` 를 구현하셔서 용도에 맞게 사용하시기 바랍니다.


This project offers the auto complete function for words that start with Korean, English, and numbers. (for UTF-8)

The difference from regular auto complete function is that this offers certain scores for each words.

it is quite useful. For example, it is able to show a number of related contents that includes the word, (just like the instagram search function).

Also, It is better to set up `IAutoCompeletManager` to use it in specific ways that you want.


# Requirements
* Java 1.6+ (this project is set up with version 1.8)
* [Spring boot](http://projects.spring.io/spring-boot/) 1.2.8+
* spring-boot-starter-redis
* [Redis](http://redis.io/) 2.4+

# Usage
1. install redis in your server or cloud server(e.g aws, azure..)
1. fork or clone this package.
1. update redis host and port in `application.yml`.
1. just run this application(STS, eclipse, intelliJ or maven).

## Sample API

- **GET** /api/{word}  // auto complete
- **PUT** /api/add/{word} // add word
- **POST** /api/{word}  // it raises a score of the word.

## Author
OKIHOUSE
