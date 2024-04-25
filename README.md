# loginJwt

Este proyecto es una aplicación de Spring Boot que proporciona un sistema de inicio de sesión completo utilizando JSON Web Tokens (JWT).

## Descripción del Proyecto

El proyecto está basado en Spring Boot y Java 17, utilizando Maven como gestor de dependencias. Proporciona un sistema de gestión de inicio de sesión completo con autenticación JWT. Incluye un CRUD básico para gestionar usuarios y roles, con una capa de seguridad integrada proporcionada por Spring Security.

## Dependencias

- **spring-boot-starter-data-jpa**: Incluye Spring Data JPA con Hibernate para la persistencia de datos.
- **spring-boot-starter-security**: Proporciona características de seguridad de Spring Boot, como la configuración de autenticación y autorización.
- **spring-boot-starter-web**: Proporciona características de desarrollo web de Spring Boot, incluido el soporte para RESTful APIs.
- **modelmapper**: Biblioteca para mapeo de objetos Java.
- **springdoc-openapi-starter-webmvc-ui**: Genera documentación OpenAPI (anteriormente conocida como Swagger) para la API REST.
- **mysql-connector-j**: Conector JDBC para MySQL.
- **lombok**: Biblioteca para simplificar el desarrollo en Java eliminando la necesidad de escribir código repetitivo, como getters y setters.
- **spring-boot-starter-test**: Incluye dependencias para escribir pruebas unitarias y de integración en Spring Boot.
- **springfox-boot-starter**: Integración de Springfox con Spring Boot para generar documentación Swagger.
- **spring-security-test**: Dependencia para realizar pruebas de seguridad en Spring Boot.
- **jjwt-api**: API de JWT para Java.
- **jjwt-impl**: Implementación de JWT para Java.
- **jjwt-jackson**: Integración de Jackson con JWT para Java.

## Configuración

El proyecto está configurado para utilizar Java 17. Asegúrate de tener Java 17 instalado en tu sistema.

Para ejecutar la aplicación, puedes utilizar el comando `mvn spring-boot:run` o puedes construir el archivo JAR ejecutando `mvn clean package` y luego ejecutando `java -jar target/loginJwt-0.0.1-SNAPSHOT.jar`.

Asegúrate de configurar correctamente la conexión a la base de datos MySQL en el archivo `application.properties`.

## Rutas

Las rutas principales de la aplicación son las siguientes:

- **Inicio de sesión**: `http://localhost:8083/login`

## Ejecución de Pruebas

Para ejecutar las pruebas, utiliza el comando `mvn test`.

## Desarrollo Adicional

Para obtener más ayuda sobre Spring Boot, consulta la [documentación oficial de Spring Boot](https://spring.io/projects/spring-boot).

