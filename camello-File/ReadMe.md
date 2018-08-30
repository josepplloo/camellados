Camel Project for Blueprint 
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache Karaf.
You can run the following command from its shell:

    osgi:install -s mvn:com.mycompany/camel-blueprint/1.0.0-SNAPSHOT

For more help see the Apache Camel documentation

    http://camel.apache.org/

#You should consider the follow snipet:    

```java
package com;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CreationofFilesTest extends CamelTestSupport {
	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new LaRutaCSV2Class();
	} 
	
	@Test
	public void testCSV() throws Exception {
		template.sendBodyAndHeader("file://in", "Txt file",
				Exchange.FILE_NAME, "test.csv");
		
		Thread.sleep(1000);
		File filecsv = new File("in/test.csv");
		assertTrue("Txt not moved",filecsv.exists());

	}
	@Test
	public void testCSVcontent() throws Exception {
        
		MockEndpoint contentEndpoint = getMockEndpoint("mock:contentEndpoint");
		contentEndpoint.expectedMessageCount(0);
		
		template.sendBody("direct:contentEndpoint", "Hello World whitout comma");
		assertMockEndpointsSatisfied();
	}

}
```
