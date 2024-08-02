## JESUS GONZALEZ FERNANDEZ - Projecto de prueba _"Precios"_  para Inditex

Para **ejecutar el microservicio**, ejecuta la clase **PricesApplication**, asegurándote de introducir los siguientes parámetros de ejecución:
* Variables de la JVM:
    * **-Dspring.profiles.active=local**
* Variables de entorno:
    * Nombre: DB_H2_PASSWORD
    * Valor: password

Si se desea revisar la **compilación en un IDE** es importante tener dentro del Build Path las siguientes carpetas:
* src/main/java
* src/main/resources
* target/generated-resources/annotations (clases generadas por el mapstruct processor)
* target/generated-resources/openapi/src/main/java (clases generadas por el plugin openapi-generator)

Para lanzar los tests unitarios y de integración basta con ejecutar la tarea maven "test" desde consola o desde el IDE.

## CURL de ejemplo para la ejecución de la búsqueda

```sh
curl --location 'http://localhost:8080/v1/prices?applicationDate=2020-06-14T17:00:01.00Z&productId=35455&brandId=1
```

Donde:

- **applicationDate**: es la fecha de búsqueda
- **productId**: es el ID único del producto
- **brandId**: es el ID único de la marca/cadena

Opcionalmente se puede especificar:
- **currency**: por defecto es EUR

## Notas de desarrollo:
- Respecto a la **especificación** de la prueba, se ha intentado realizar la implementación más acotada y acorde posible al enunciado, sin modificar el tipo de los IDs (que podrían ser UUID) ni crear tablas satélite (BRAND, PRICE_LIST, PRODUCT). Únicamente se realizaron 2 pequeños ajustes:
    - El nombre de la columna "PRICE_LIST" se modificó por "PRICE_LIST_ID", por buenas prácticas de **Clean Code**
    - Dentro de los parámetros de búsqueda se introdujo la CURRENCY (por defecto EUR si viene a null en la request), para asegurar la unicidad de los resultados de búsqueda, suponiendo que pudieran existir varios registros en la BD con los mismos datos (incluida la prioridad) pero con diferente moneda.


- Respecto a la **implementación**:
    - Se utiliza arquitectura hexagonal pura, con separación e independencia del framework a nivel de domain y application
    - La implementación de los adaptadores de entrada y salida (REST Controller y JpaRepository) utilizan SpringFramework
    - La contraseña de la BD se lee como parte de las variables de entorno, aunque podría leerse cifrada de un servidor de configuración externo
    
    - Se usaron librerías externas como Lombok y Mapstruct

## Testing
- **E2E**: se adjunta colección Postman (**src/test/resources/InditexPrices.postman_collection.json**) con las pruebas e2e definidas en el enunciado. Para ejecutarlas, importar en Postman, seleccionar la colección y ejecutar (Run).
- **Integración** con MockMVC: se ejecutan en el perfil por defecto de pruebas de maven (mvn test), ejecutando también los 5 casos expuestos en el enunciado. Utiliza el perfil "test" de Spring para leer las propiedades del archivo application-test.yml
- **Unitarios**: existen diversas clases probadas con tests unitarios con Mockito. No se realizaron tests para probar clases o métodos generados, a excepción de los mappers (ej, los toString, hashCode, equals, etc).
- Cobertura: se incluye plugin de Jacoco para obtener la cobertura de código mediante los tests
