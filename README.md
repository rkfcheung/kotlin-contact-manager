# kotlin-contact-manager
Contact Manger in Kotlin

## Spring Initializr

* [contact.zip](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=2.2.4.RELEASE&packaging=jar&jvmVersion=11&groupId=com.rkfcheung&artifactId=contact&name=contact&description=Contact%20Manager%20for%20Spring%20Boot%2FKotlin&packageName=com.rkfcheung.contact&dependencies=web,security,actuator,devtools,thymeleaf,webflux,session,cloud-connectors,data-jpa,h2)
* [Help](HELP.md)

## Heroku

* [Getting Started on Heroku with Java](https://devcenter.heroku.com/articles/getting-started-with-java)

## Limitations

1. User H2 as a database but it can be replaced by another database or NoSQL.
2. There is a problem on SecurityConfig so it can't be login.
3. Selenium tests (for UI) are not implemented yet.

## Scalability

1. Integration tests on `ContactService` and `UserService` are defined so it shall be able to port to another DB/NoSQL.
2. Data layer can be moved to another service to make it more robust.
3. Using Heroku can be scaled up easily by using [`heroku ps:scale web=X`](https://devcenter.heroku.com/articles/scaling).
