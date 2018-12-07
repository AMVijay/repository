# Project to Create Java Classes from WSDL

## Approach 1 using wsimport
* `wsimport <wsdl file path/url> -verbose -keep -d generated` 
* Exaxmaple  `wsimport src/main/resources/BLZService.wsdl -verbose -keep -d src/main/java/`


## Approach 2 using pom.xml
* Refer pom.xml available in this directory for reference
* Execution command `mvn clean install -DskipTests`
* Reference: http://www.mojohaus.org/jaxb2-maven-plugin/Documentation/v2.4/xjc-mojo.html
### Technical Stack Used 
* JDK 1.8
* org.codehaus.mojo:jaxb2-maven-plugin:2.4
    * Key Items: 
        * `<sourceType>wsdl</sourceType>` determines what the source type is. It could be wsdl, xsd, dtd.
        * `<outputDirectory>` is for where the generated java source copied.
        * `<sources>` - is for where the wsdl, xsd, dtd source files present for generating classes.
