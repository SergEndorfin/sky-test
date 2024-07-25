# Sky test task

Project requirements described in the "Sky-task-requirements.pdf" file of the projects root folder.
<hr>
<b>Project consists of 2 modules:</b>

1. <i>sky-test-api</i>: (OpenApi spec holder, code generator (DTOs), source of truth API)
2. <i>sky-test-server</i>: Spring Boot REST App based on generated DTOs of the "sky-test-api"

<hr>

Technologies and library used:
- Spring Boot 3
- Spring Boot Data JPA
- Spring Security (Basic Auth)
- Lombok
- Mapstruct
- Docker + docker-compose
- MySql, H2
- OpenAPI 3
- Maven
- Junit 5, Mockito, Swagger-request-validator-spring-webmvc
- Java 21
<hr>

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
<hr>

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/SergEndorfin/sky-test
    ```
2. Navigate to the project directory:
    ```sh
    cd project
    ```
3. Build the project:
    ```sh
    mvn clean install
    ```
   Note: It is necessary to run exactly "mvn install" first time to populate your local .m2 local Maven folder with the "sky-test-api" library,
because "sky-test-api" does not store in any remote repositories.
<hr>

## Usage

There are 3 profiles configured in the project: default, dev, local.

To run the "sky-test-server" App with imbedded H2 DB you need to use "local" profile
(all integration tests using "local" profile):

```sh
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

You can run "mysqldb" service from docker-compose file and "dev" and "default" profile will 
interact with MySql DB:
```sh
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

If you want to test "ready to use" solution on your local, you can run
```sh
docker compose up --build
```
it will build the image based on Dockerfile from the "sky-test-server" folder and
run both "sky-test-server" App and MySql containers.
Then you can find "Sky-test.postman_collection.json" file in the root folder,
put it into the Postman App and send requests to the endpoints of the running App with Basic Auth.
For this, firstly, you need to fire "POST http://localhost:8080/v1/users" endpoint to create the user.
<hr>

## Contributing

"API first" approach has been implemented.
To add a new endpoint you need to start from the "<i>sky-test-api</i>" specification module.
1. Add spec info into OpenApi yml files.
2. Then follow the TDD approach: add integration test like in "UserControllerApiValidationTest" class of the
"<i>sky-test-server</i>" module with expected results to make sure API is fine.
3. Add Controller endpoint, Service, Repository stuff
4. Add unit tests for mapping (DTO to entity) and for everything from Step 3.

!! Each layer should be tested separately by utilizing Junit 5 and mockito. !!
