
## curl installieren
Installier curl: 
macOS: brew install curl
Windows: https://stackoverflow.com/a/16216825/5155817
Mit curl -v ... werden die Details der Kommunikation via HTTP angezeigt.

## tasks

- HTTP Verbs

- content negotiation / representation (HTML vs JSON)? http://www.baeldung.com/spring-mvc-content-negotiation-json-xml mal mit curl beides abfragen und sehen wie json und xml kommt
  - mit curl nach xml fragen und dabei scheitern (406)
  - read https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html#howto-write-an-xml-rest-service
  - add 2 dependencies 1 one annotation
  - `curl -v -H "Accept: application/xml" http://localhost:8080/departments`

- validierung
  - enablen
  - mal mit curl nen kaputten employee schicken.

- hateoas einbauen
  - initial: employees haben auf department @JsonIgnore gesetzt
  - alle dinger mit selfrel
  - mal die employees ins department mal linken
  - support für embedded scheint nicht so gut - man sieht nur beispiele, wo sich die leute das selbst bauen.
  - https://docs.spring.io/spring-hateoas/docs/current/reference/html/
  - http://www.baeldung.com/spring-hateoas-tutorial

- employee posten / putten mit link

  curl -H "Content-Type: application/json" -X POST -d '{"firstName":"Tim","lastName":"Taylor","_links":{"department":{"href":"http://localhost:8080/departments/1"}}}' http://localhost:8080/employees
  - die beziehung von employee zu department ist nicht persistiert. Diskutiere folgende Ansätze
    - das links-element in der create-methode auswerten.
    - ein neues attribut einführen - department-ID
    - URLs umstrukturieren - /departments/{id}/employees/{id} 
    - weitere ideen?

### swagger stuff

- https://indrabasak.wordpress.com/2016/04/07/swagger-2-integration-with-spring-rest/
- 2 dependencies hinzufügen
- 1 configuration klasse
- URL aufrufen http://localhost:8080/swagger-ui.html

### exception handling

- https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-exceptionhandler
- http://localhost:8080/employees/8 -> exception zeigt 500 und zu viele interne daten an
- erfinde exception handler, der entity not found exceptions in 404s mappt

## open issues

- funny root element name: curl -v -H "Accept: application/xml"  http://localhost:8080/employees/1
  - in XML: employee item in department employees collection is called employees but should be employee
- needed to annotate Employee with @JsonIgnoreProperties - can we get rid of this?