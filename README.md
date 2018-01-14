# RESTful Services examples

Examples about RESTful Services in Java, created with Spring Boot.
Uses JPA, Exceptions, embebbed H2, hateoas, Jackson and Swagger.

## Helo World

Hello world first examples

* Hello World with a String (/helloworld)
* Hello World with a Bean and Jackson conversion to JSON (/helloworld-bean)
* Hello World with a path variable (/helloworld/{name})
* Hello World with internationalized language (/helloworld-internationalized + Accept-Language=xx)

You can see sources in [this link](https://github.com/ojoDev/restful/tree/master/restful-web-services/src/main/java/com/ojodev/rest/webservices/restfulwebservices/helloworld)

## Filtering 

Filter annotations in JSON

* Filtering a bean (/filtering)
* Filtering a list of beans (/filtering-list)

You can see sources in [this link](https://github.com/ojoDev/restful/tree/master/restful-web-services/src/main/java/com/ojodev/rest/webservices/restfulwebservices/filtering)

## Versioning

4 methods to verioning your RESTful services

* Versioning in mapping (v1/person, v2/person)
* Versioning with query param (/person/param?version=1, /person/param?version=2)
* Versioning with header params (/person/header + X-API-VERSION=1, /person/header + X-API-VERSION=2)
* Versioning with produces (/person/produces + application/vnd.company.app-v1+json, /person/produces + application/vnd.company.app-v2+json)

You can see sources in [this link](https://github.com/ojoDev/restful/tree/master/restful-web-services/src/main/java/com/ojodev/rest/webservices/restfulwebservices/versioning)

## Complete example

Complete RESTful example with users.

* Get users (/users)
* Get user (/users/{id})
* POST user (/users)
* Delete user (/users/{id})

You can see sources in [this link](https://github.com/ojoDev/restful/tree/master/restful-web-services/src/main/java/com/ojodev/rest/webservices/restfulwebservices/user)

## Complete example with JPA

Complete RESTful example for a Blog backend with JPA

* Get users (/jpa//users)
* Get user (/jpa//users/{id})
* POST user (/jpa//users)
* Delete user (/jpa//users/{id})
* Get post (/jpa/users/{id}/posts)
* POST post (/jpa/users/{id}/posts)

You can see sources in [this link](https://github.com/ojoDev/restful/tree/master/restful-web-services/src/main/java/com/ojodev/rest/webservices/restfulwebservices/user)

