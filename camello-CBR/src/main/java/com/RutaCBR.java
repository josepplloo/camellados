package com;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class RutaCBR extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:start")
		.choice()
		.when(body().contains("Camel"))
		.setHeader("verified", simple("true"))
		.to("mock:camel")
		.when(body().contains("Hello"))
		.to("mock:other")
		.otherwise()
		.log(LoggingLevel.WARN, "No assert whit message ${body}");
	}

}
