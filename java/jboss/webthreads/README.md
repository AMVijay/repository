# POC to Prove Jboss Http Threads dynamically allocated by default.

## Technical Stack
* Java 1.8
* Jboss EAP 6.x/7.x
* Maven

## Notes
* Create Project using maven command 
`mvn archetype:generate -DgroupId=vijay.poc.jboss -DartifactId=webthreads -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false -Djavax.net.ssl.trustStore=repomavenapacheorg.jks`
* Create WAR file using command `mvn clean install -DskipTests -Djavax.net.ssl.trustStore=repomavenapacheorg.jks`
* Deploy the project in Jboss
* Run ThreadTest.java from test source folder.

## Jboss Documentation: 

If the max-connections attributes is not set on web subsystem connectors in standalone-(*).xml / domain.xml, default is computed as:

    512 * Runtime.getRuntime().availableProcessors() for default Java connector
    32 * Runtime.getRuntime().availableProcessors() for native APR connector addon


* https://access.redhat.com/solutions/25054