package com;

import static org.junit.Assert.assertEquals;

import java.util.Date;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import com.Event;

public class JpaWithNamedQueryTest {
    
	private Event event;
	
	    
    @Test
    public void hhh123Test() throws Exception {
        Configuration configuration = new Configuration();
        
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        // Do stuff...
        
        event = new Event();
        event.setCreatedOn(new Date());
        event.setEvent_Name("Pepito");
        
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
}
