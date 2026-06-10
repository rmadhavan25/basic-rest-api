# AGENTS.md

## Project Facts

- Spring Boot Maven app using Java 17 and Spring Boot parent `4.0.6`.
- Maven wrapper uses Maven `3.9.16` with `distributionType=only-script`; `.mvn/wrapper/maven-wrapper.jar` is intentionally ignored.
- Main package and component-scan root: `com.madhavan.basicrestapi`.
- App entrypoint: `src/main/java/com/madhavan/basicrestapi/BasicRestApiApplication.java`.

## Commands

- Run tests: `./mvnw test`
- Run the app: `./mvnw spring-boot:run`
- Build package: `./mvnw package`
- Run one test class: `./mvnw -Dtest=BasicRestApiApplicationTests test`
- Run one test method: `./mvnw -Dtest=BasicRestApiApplicationTests#contextLoads test`

## Current Shape

- No controllers, services, entities, repositories, or migrations exist yet.
- `spring-boot-starter-data-jpa` is present, but there is no database driver or datasource config.
- `application.properties` only sets `spring.application.name=basic-rest-api`.
- The only test is a generated `@SpringBootTest` context-load smoke test.

## Repo Notes

- No `README*`, CI workflow, existing agent instructions, or repo-local OpenCode config currently exists.
- `HELP.md` exists but is ignored by `.gitignore`; do not treat it as maintained project documentation.
