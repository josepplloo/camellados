package com;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

public class Ruta extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		BindyCsvDataFormat bindycsvformat = new BindyCsvDataFormat(Event.class);
		from("file:in?noop=true")
		.unmarshal(bindycsvformat)
		.split(body())
		.log(LoggingLevel.INFO, "Process Event-> ${body.id}")
		.to("jpa:"+Event.class.getName()+"?persistenceUnit=templatePU")
		.log(LoggingLevel.INFO, "Inserted new Event ${body.id}");
	
		from("direct:csv")
		.unmarshal(bindycsvformat)
		.split(body())
		.to("jpa:event?persistenceUnit=templatePU")
		.to("mock:jpa");
		
		from("jpa:event?consumer.namedQuery=event.findall&persistenceUnit=templatePU")
		.log(LoggingLevel.INFO, ">>>>>>>>>>>>>>>>>>>>>> ${body}")

		.split(body())
		.marshal(bindycsvformat)
		.log(LoggingLevel.INFO, "----> ${body}")
		.to("mock:marshal");
	}

	
}
