# cryptotrading

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher
- [Maven 3.8.6](https://maven.apache.org) or higher

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.practice.cryptotrading.CryptotradingApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Build the application

You can build the project and run the tests by running

```shell
mvn clean package
```

Or you can build without running unit tests

```shell 
mvn clean package -Dmaven.test.skip=true
```

Once the build process is successful, you can run the application in `target` folder 

```shell
java -jar ./target/cryptotrading-<version>.jar
```

Default port is `8080`, to override the port number you can add `--server.port=<port_number>` in to your command

```shell
java -jar ./target/cryptotrading-<version>.jar --server.port=8081
```

## Get information about system health
```
http://localhost:8080/actuator/health
http://localhost:8080/actuator/health/liveness
http://localhost:8080/actuator/health/readiness
```

## Download Open API Doc
```
http://localhost:8080/api-docs.yaml
```
