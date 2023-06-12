# ML Service Front-end
Starting from this [tutorial](https://spring.io/guides/gs/handling-form-submission/)
to build a front end service for using ml applications. This front-end service is used for taking users' requests for
the ML service [[GitHub Repo]](https://github.com/allyoushawn/mlservice)

To run the service, use the green triangle of the MlservicefrontendApplication. 
After the service is running, hit the following endpoint to see the service page:
```
http://localhost:8080/ml_service
```
## Lombok Note
The repository use lombok plugin with IntelliJ to make the code more concise.
To successfully compile with the lombok plugin, follow the guidance [here](https://www.baeldung.com/lombok-ide).
Also, please make sure the lombok version is aligned with [ML service](https://github.com/allyoushawn/mlservice).


## Configs
The front end service. Using [Spring initializer](https://start.spring.io) for initializing.
* Project: Gradle-Groovy
* Language: Java
* Spring Boot: 2.7.7
* Packaging: Jar
* Java: 11