# natural-numbers-interpretation

### Requirements
- Java 8
- Apache Maven 3.6.3
- Wildfly-23.0.2 or any other Application Server that supports the RESTEasy JAX-RS implementation (eg Tomcat)

<br>

### Compile
You can find a guide on how to install Maven on Windows [here](https://howtodoinjava.com/maven/how-to-install-maven-on-windows/)

In order to compile the application, use the Maven. Run the command **mvn clean install** in the {PROJECT_ROOT_FOLDER}, in which the pom.xml appears.

If the compilation process was successful, then the file **natural-numbers-interpretation.war** should appear under the {PROJECT_ROOT_FOLDER}/target/

<br>

### Deploy
Download Wildfly from [here](https://www.wildfly.org/)

1. Copy the above .war file and paste it under the {WILDFLY_ROOT_FOLDER}/standalone/deployments
2. Open a terminal and navigate to {WILDFLY_ROOT_FOLDER}/bin
3. execute from command line <b><i>standalone.bat</i></b>

If everything has been done correctly then if you navigate to **http://localhost:8080/natural-numbers-interpretation/** you should see the message **Application is up and running**.
<br>

**Note:** You can automate the compile-and-deploy process by adding the Wildfly to your IDE configuration (for IntelliJ - look [here](https://www.jetbrains.com/help/idea/configuring-and-managing-application-server-integration.html))

<br>

### How-to
Since the application does not have any front end implementation, in order to use the functionality, an endpoint has been exposed at <http://localhost:8080/natural-numbers-interpretation/validate/number/put your number here>

You can either use a browser or any  API client (like [Postman](https://www.postman.com/)). In case you use Postman, make sure to preview the response as HTML.

<br>

### Example
If you want to find all the interpretations of the number **0 0 30 69 5 63 40 564**, you can just search for
<br>
<http://localhost:8080/natural-numbers-interpretation/validate/number/0 0 30 69 5 63 40 564>

The output should be: <br>

Input telephone number is: 00306956340564

Interpretation 1: 0030609563405604 [phone number: INVALID]

Interpretation 2: 003060956340500604 [phone number: INVALID]

Interpretation 3: 003060956340564 [phone number: INVALID]

Interpretation 4: 00306095634050064 [phone number: INVALID]

Interpretation 5: 00306956340500604 [phone number: INVALID]

Interpretation 6: 003069563405604 [phone number: INVALID]

Interpretation 7: 0030609560340500604 [phone number: INVALID]

Interpretation 8: 0030695603405604 [phone number: INVALID]

Interpretation 9: 0030609560340564 [phone number: INVALID]

Interpretation 10: 003060956034050064 [phone number: INVALID]

Interpretation 11: 00306956340564 [phone number: VALID]

Interpretation 12: 00306956034050064 [phone number: INVALID]

Interpretation 13: 0030695634050064 [phone number: INVALID]

Interpretation 14: 00306095603405604 [phone number: INVALID]

Interpretation 15: 003069560340564 [phone number: INVALID]

Interpretation 16: 003069560340500604 [phone number: INVALID]
