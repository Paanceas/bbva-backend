
# Proyecto Backend de Conversión de Divisas

Este proyecto consiste en un microservicio para la conversión de divisas utilizando Java Spring Boot. El microservicio obtiene las tasas de cambio de un servicio externo y permite convertir montos entre diferentes divisas.

## Requisitos Previos

1. Java 17 o superior
2. Maven 3.6.3 o superior
3. Una herramienta para realizar peticiones HTTP (por ejemplo, Postman)
4. IDE de tu preferencia (por ejemplo, IntelliJ IDEA, Eclipse, VS Code)

## Configuración del Proyecto

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/Paanceas/bbva-backend.git
   cd tu-repositorio
   ```

2. **Configura el archivo `application.properties`:**

   En el directorio `src/main/resources`, edita el archivo `application.properties` para incluir tu clave API:

   ```properties
   external.api.url=http://api.exchangeratesapi.io/v1/latest
   external.api.key=tu_external_api_key_aqui
   api.key=your_api_key_here
   ```
3. **Puedes configurar tus cors si asi lo prefieres:**

   En el directorio `src/main/resources`, edita el archivo `application.properties`:
   ```properties
   cors.allowed.origins=http://localhost:4200
   cors.allowed.methods=GET,POST,PUT,DELETE,OPTIONS
   cors.allowed.headers=*
   cors.allow.credentials=true
   ```
  

## Estructura del Proyecto

- **Controladores:**
  - `CurrencyConversionController.java`: Maneja las peticiones para la conversión de divisas.
  - `CurrencySymbolsController.java`: Maneja las peticiones para obtener los símbolos de las divisas.

- **Servicios:**
  - `CurrencyConversionService.java`: Contiene la lógica para la conversión de divisas.
  - `ExternalCurrencyService.java`: Interactúa con el servicio externo para obtener las tasas de cambio.

- **DTOs (Data Transfer Objects):**
  - `ConversionRequest.java`: Representa la solicitud de conversión de divisas.
  - `ConversionResponse.java`: Representa la respuesta de la conversión de divisas.

- **Utilidades:**
  - `ApiKeyValidator.java`: Valida la clave API proporcionada en las peticiones.

- **Interceptores:**
  - `ApiKeyInterceptor.java`: Interceptor para validar la clave API en cada petición.

- **Configuraciones:**
  - `WebConfig.java`: Configura los interceptores y CORS.

- **Manejadores de Excepciones:**
  - `GlobalExceptionHandler.java`: Maneja las excepciones globales de la aplicación.

## Ejecución del Proyecto

1. **Compila y ejecuta el proyecto:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

2. **Prueba los endpoints:**

   - **Conversión de divisas:**

     ```bash
     POST http://localhost:8080/api/v1/currency-conversion
     Headers: { "API-KEY": "tu_api_key_aqui", "Content-Type": "application/json" }
     Body: 
     {
       "amount": 100,
       "fromCurrency": "USD",
       "toCurrency": "EUR"
     }
     ```

   - **Obtener símbolos de divisas:**

     ```bash
     GET http://localhost:8080/api/v1/currency-symbols
     Headers: { "API-KEY": "tu_api_key_aqui" }
     ```

## Dependencias

El proyecto utiliza las siguientes dependencias:

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.0.2</version>
		</dependency>
	</dependencies>
```

## Documentación con Swagger

El proyecto incluye documentación de la API generada automáticamente con Swagger. Para acceder a ella, navega a:

```
http://localhost:8080/swagger-ui.html
```

## Contribuciones

Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva característica'`).
4. Empuja la rama (`git push origin feature/nueva-caracteristica`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

### AUTOR

Este proyecto ha sido desarrollado por **Pablo Ceballos**.

* Puedes encontrar mas repositorios del autor en GitHub siguiendo [este enlace](https://github.com/Paanceas).
