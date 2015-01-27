# OcejpadTests
Simple project that contains tests that helped me to prepare for Oracle Certified Expert JPA Developer. Runs on OpenEJB and HSQLDB.

To build and execute all of the tests simply run

    maven clean package

## Additional info

1. You can add DB data source to IntelliJ IDEA - when mvn package is done there will be `test.script` HSQLDB file generated in `target/db/test`. You can use hsqldb-2.3.2.jar as a JDBC driver to 'connect' to this file.
2. `ContextUtils` does the EJB lookup, since Injection was not working properly in test environment. If you add a new EJB beans you can use this class.
3. There is also a simple web service so that you can test code quickly outside OpenEJB (for example JBoss AS)

## More materials online

* [http://www.thejavageek.com/2014/09/17/summary-annotations-elements-jpa/](http://www.thejavageek.com/2014/09/17/summary-annotations-elements-jpa/)
* [http://www.objectdb.com/api/java/jpa](http://www.objectdb.com/api/java/jpa)
* [http://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html](http://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html)
