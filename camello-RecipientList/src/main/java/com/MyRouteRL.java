package com;
//http://camel.apache.org/jaxb

import javax.xml.bind.JAXBContext;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;


public class MyRouteRL extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(Student.class);
		xmlDataFormat.setContext(con);
				
		from("direct://filexml")
		.unmarshal(xmlDataFormat)
		.setHeader("promedio",simple("${body.promedio}"))
		.log(LoggingLevel.INFO, "------------> ${header.promedio} \n  ")
		.to("velocity:studentTemplate.vm")
		.to("mock:velocity")
		.unmarshal(xmlDataFormat)
		.bean(MessageRouter.class, "routeTo");
	}

}
