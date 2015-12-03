# Description
해당 프로젝트는 한글, 영어, 숫자로 시작하는 단어의 자동완성기능을 구현하고 있습니다.

This project offers the auto complete function for words that start with Korean, English, and numbers. (for UTF-8)

# Requirements
* Java 1.6+ (this project is set up with version 1.8)
* [Spring boot](http://projects.spring.io/spring-boot/) 1.2.8+
* spring-boot-starter-redis
* [Redis](http://redis.io/) 2.4+

# Usage
1. install redis in your server or cloud server(e.g aws, azure..)
1. fork or clone this package.
1. update `application.yml` redis host and port.
1. just run this application(STS, eclipse, intelliJ or maven).

# API

## Auto complete
- **GET** /api/{word} 


# Author
OKIHOUSE
