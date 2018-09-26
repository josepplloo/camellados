Camel Project for Blueprint 
===========================

### Tip
    See the tests, consider the folow snipet!


```java
package com;


import java.util.Date;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import com.Event;

public class JpaWithNamedQueryTest extends CamelTestSupport {
    
	private Event event;
	

	protected RouteBuilder createRouteBuilder() throws Exception{
		return new Ruta();
	} 
	
	    
    @Test
    public void h2Test() throws Exception {
        Configuration configuration = new Configuration();
        
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        // Do stuff...
        
        event = new Event();
        event.setCreatedOn(new Date());
        event.setName("Pepito");
        event.setId((long) 1);
        
        session.getTransaction().begin();
        
        session.persist(event);
          Event dbEvent = session.createQuery(
                "select e " +
                "from Event e", Event.class)
            .getSingleResult();
        assertEquals(event.getCreatedOn(), dbEvent.getCreatedOn());
        
        session.getTransaction().commit();
        session.close();
    }
    
    @Test
    public void testJPAEndpoint() throws InterruptedException {
    	String event ="Id,createdOn,Name\n" + 
    			"4,05/05/05,meeting\n" + 
    			"5,05/05/05,dinner\n" + 
    			"6,05/05/05,dancing";
    	template.sendBody("direct:csv",event);
		Thread.sleep(2000);

		MockEndpoint mockjpa  = getMockEndpoint("mock:jpa");
		Event eventbean = new Event();
		eventbean = (Event) mockjpa.assertExchangeReceived(0).getIn().getBody();
		assertEquals(Event.class, eventbean.getClass());
		assertEquals("meeting", eventbean.getName());


    }
}

```


To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache Karaf.
You can run the following command from its shell:

    osgi:install -s mvn:com.mycompany/camel-blueprint/1.0.0-SNAPSHOT

For more help see the Apache Camel documentation

    http://camel.apache.org/
