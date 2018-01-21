# RESTful Services mit Spring Boot 

Starte die Applikation:

    mvn spring-boot:run
    
Du kannst nun die Applikation unter folgenden URLs aufrufen: 
- http://localhost:8080/employees
- http://localhost:8080/departments

## cURL

Um mit dieser Übung arbeiten zu können, solltest Du cURL installieren. 

- macOS: `brew install curl`
- Windows: https://stackoverflow.com/a/16216825/5155817

Hinweis: Mit `curl -v ... werden die Details der Kommunikation via HTTP angezeigt.

## Aufgaben

### API Dokumentation mit Swagger

Erweitere das Projekt, sodass die API-Dokumentation mit Swagger UI angezeigt wird.

Füge dazu im POM folgende Dependencies hinzu: 

    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>${springfox.swagger.version}</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>${springfox.swagger.version}</version>
    </dependency>
    
Setze das Property `springfox.swagger.version` auf `2.7.0`.

Füge in der Klasse `ch.juventus.example.ExampleApplication` folgende Annotation auf Klassenebene hinzu `@EnableSwagger2`

Nun kannst Du Swagger UI unter folgender URL aufrufen: http://localhost:8080/swagger-ui.html
Versuche, über die Weboberfläche einige Requests an die Applikation zu senden.

### Content negotiation

Rufe die gleiche Ressource in verschiedenen Formaten auf:

- JSON: `curl -v http://localhost:8080/departments`
- XML: `curl -v -H "Accept: application/xml" http://localhost:8080/departments`

Was bedeutet der HTTP Statuscode 406?

Füge Support für XML mit Hilfe folgenden Abschnitts hinzu: 
https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html#howto-write-an-xml-rest-service

### HATEOAS Support

Der `DepartmentController` dekoriert die Ressource `Department` mit Links. 
Erweitere den `EmployeeController`, sodass die Ressource `Employee` mit folgenden Links dekoriert wird:
- `_self`-Link mit Link auf die eigene URL.
- 'department`- Links, der die URL des verlinkten Departments enthält.

### POST-Request

Erzeuge einen neuen Employee mit folgendem POST-Request:

    curl -v -H "Content-Type: application/json" POST -d '{"firstName":"Heidi","lastName":"Keppert","_links":{"department":{"href":"http://localhost:8080/departments/1"}}}' http://localhost:8080/employees

Verifiziere, dass d

- employee posten / putten mit link

  
  - die beziehung von employee zu department ist nicht persistiert. Diskutiere folgende Ansätze
    - das links-element in der create-methode auswerten.
    - ein neues attribut einführen - department-ID
    - URLs umstrukturieren - /departments/{id}/employees/{id} 
    - weitere ideen?



### exception handling

- https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-exceptionhandler
- http://localhost:8080/employees/8 -> exception zeigt 500 und zu viele interne daten an
- erfinde exception handler, der entity not found exceptions in 404s mappt

## open issues

- funny root element name: curl -v -H "Accept: application/xml"  http://localhost:8080/employees/1
  - in XML: employee item in department employees collection is called employees but should be employee
- needed to annotate Employee with @JsonIgnoreProperties - can we get rid of this?