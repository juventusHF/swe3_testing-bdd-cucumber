# RESTful Services mit Spring Boot

Starte die Applikation:

    mvn spring-boot:run
    
Du kannst nun die Applikation unter folgenden URLs aufrufen: 
- http://localhost:8080/employees
- http://localhost:8080/departments

## Aufgaben

### Cucumber Tests laufen lassen

Um die Cucumber-Tests zu starten:

- Starte die Applikation (siehe oben)
- Starte `ch.juventus.example.web.RunCukesIT` in Deine IDE

Das Ergebnis sollte folgende Ausgabe sein:

    1 Scenarios (1 passed)
    3 Steps (3 passed)
    0m0.938s


    Process finished with exit code 0

### Cucumber Scenario hinzufügen

Erweitere das Test-Feature um ein neues Scenario.

> Aufruf eines nicht-existierenden Employees soll mit der passenden HTTP-Response beantwortet werden.

Du müsstest folgende Dateien erweitern:

- die Test-Scenarios in `ch/juventus/example/web/employee.feature`
- die Test-Steps in `ch.juventus.example.web.EmployeeStepDefs`

Hilfestellungen:
- Welches Scenario existiert schon in `employee.feature`?
- Wie sind die benutzten Given - When - Then Schritte `EmployeeStepDefs` abgebildet?
- Wie würde eine neues Scenario aussehen, welches nach einem nicht existierenden Employee fragt (z.B. mit `5` als ID)?
- Was fehlt in `EmployeeStepDefs`, um das neue Scenario zu implementieren?

