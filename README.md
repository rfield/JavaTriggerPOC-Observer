JavaTriggerPOC - Observer
========================= 

Author:	Richard Field
Date:	July 2013

In legacy applications, database tables often need to participate in the well-known Observer pattern, meaing that as the data in the table changes, application code needs to be executed.
The typical programming idiom for this situation involves a database trigger associated with the table of interest. Often the trigger inserts new records into a "shadow table" whenever an update occurs. The shadow table is checked using some sort of polling mechanism, either via a brute force method like Unix cron or using a third party adapter provided by an integration platform such as the Mule ESB, Apache Camel or TIBCO BusinessWorks.

In general, Polling, or "repeated refresh", though simple, is more or less an architectural anti-pattern, despite its popularity.

This project is part of a working proof-of-concept demonstrating the use of Oracle Triggers executing a Java Procedure in order to "publish" data changes to a process running outside the database.


The overall POC consists of 3 parts:

The Observer
------------
The current implementation is a RESTful service, invoked via HTTP POST.

The Subject
-----------
The Oracle database table which needs to be "observed" for changes in state.

The Publisher
-------------
An Oracle trigger which invokes a Java-based Stored Procedure to execute the HTTP POST.


This project, JavaTrigger-POC - Observer, consists of the Observer code.

For convenience, the Subject and Publisher components are in a separate project. This is because Oracle 11g supports Java 1.5. So the Subject and Publisher must be built with Java 1.5. The Observer component, which uses Spring MVC REST, requires Java 1.6 annoation support.


Set Up
------
You must have JDK 1.6 installed. The POM files will downgrade to 1.5 compatibility where required.

You must also have access to an Oracle Enterprise database. Oracle Express Edition (Oracle XE) does not support Java Triggers, though the loadjava/dropjava commands are available which is helpful.


Instructions
------------

1) mvn clean
2) mvn compile
3) mvn package
4) deploy the javatrigger.war into Tomcat
5) Use the Firefox REST Client Tool or the included RestClient.java to test the Observer

If you use Firefox try these requests:

Method: GET;  URL: http://localhost:8080/javatrigger/poc/movie/2
Method: POST; URL: http://localhost:8080/javatrigger/poc/movie/add; Request Body: {"price":1.95,"title":"High Noon","id":0}

and make sure you set the following custom headers:

Accept: application/json
Content-Type: application/json