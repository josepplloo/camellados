package com;

import org.apache.camel.Exchange;
import org.apache.camel.RecipientList;

public class MessageRouter {
	@RecipientList
	public String routeTo(Exchange ex) {
		Student estudiante = ex.getIn().getBody(Student.class);
		String mock = "mock:winner";
		System.out.println(estudiante.curso);
		if (estudiante.curso.equals( "Aprobado")) { 
			mock = "mock:winner";
			return mock;
        }
		else {
			return "mock:loser";
		}
    }
}
