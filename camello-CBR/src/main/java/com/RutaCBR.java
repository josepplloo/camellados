package com;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class RutaCBR extends RouteBuilder{
	
	MyStopPolicy policy =new MyStopPolicy();
	
	

	@Override
	public void configure() throws Exception {
		
		
		
		
		from("direct:start").routeId("rutacbr")
		.choice()
		.when(body().contains("Camel"))
		.setHeader("verified", simple("true"))
		.to("mock:camel")
		.when(body().contains("Hello"))
		.to("mock:other")
		.otherwise()
		.to("direct:error");
		
		
		from("direct:error")
		.routePolicy(policy)
		.log(LoggingLevel.WARN, "No assert whit message ${body}")
		.to("mock:error");

	}

}
