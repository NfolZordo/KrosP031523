## Open Api
**Open Api url**```{host}:{port}/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-conf``` <br>
**Example:** ```http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-conf``` <br>
**Documentation for the spring Generator:** ```https://openapi-generator.tech/docs/generators/spring/``` <br>
**Template for the spring Generator (api main template):** ```https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/resources/JavaSpring/api.mustache``` <br>
**Template for the spring Generator (model main template):** ```https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/resources/JavaSpring/model.mustache``` <br>
**Template for the spring Generator (pojo main template):** ```https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/resources/JavaSpring/pojo.mustache``` <br>

## Kafdrop
**Example (local):** ```http://localhost:19000/```

#### Docker (run application)
##### *for using Dockerfile without docker-compose.yml please move Dockerfile to the project root directory (for creating correct context)

1. ```docker build -t data-manager-service .```
2. ```docker run -p 8080:8080 --name data-manager-service -d data-manager-service```
3. ```docker stop data-manager-service```
4. ```docker rm --force data-manager-service```
5. ```docker image rm data-manager-service```

##### *for using Dockerfile as a part of docker-compose.yml please run next commands: (docker-compose.yml provide correct context)
1. ```docker-compose -f docker-compose.dev.yml up -d```
2. ```docker-compose -f docker-compose.dev.yml down```

## Testcontainers

1. Do not use```System.setProperty("spring.datasource.url", db.getJdbcUrl());``` in ConfigurableApplicationContext
2. For providing DynamicPropertySource you can try to use
    ```    
   @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("db_url", db::getJdbcUrl);
        registry.add("db_username", db::getUsername);
        registry.add("db_password", db::getPassword);
    }
   ```
testcontainers.properties
   ```image.substitutor = com.ars.```
