package com;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder {


	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("timer:pepe")
		.log(LoggingLevel.INFO, "HOLA");
		
	}

}
