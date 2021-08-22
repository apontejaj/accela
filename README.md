## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.apontejaj.accela.AccelaApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

### On linux based environments
```shell
./mvnw spring-boot:run
```

### On windows 
```shell
mvnw.cmd spring-boot:run
```

Running the application will create an SQLite file called accella.db. All data persistency will be stored there.


