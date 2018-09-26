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
		
		//Route for fredys test
		
		from("direct:nominaxml")
		.to("file://in?fileName=nomina.xml");
		
		from("file://in?fileName=nomina.xml&noop=true")
		.wireTap("direct:copy")
		.to("mock:copy")
		.to("mock:info_ftp");
		
		//wiretap 
		xmlDataFormat = new JaxbDataFormat();
		con = JAXBContext.newInstance(Nomina.class);
		xmlDataFormat.setContext(con);
		
		from("direct:copy")
		.convertBodyTo(String.class)
		.to("mock:original")
		
		.unmarshal(xmlDataFormat)
		.log(LoggingLevel.INFO, "------> ${body.empleados.empleado[0].nombres} ")
		.setBody(simple("${body.empleados.empleado[0].nombres},"
				+ "${body.empleados.empleado[0].apellidos},"
				+ "${body.empleados.empleado[0].documento},"
				+ "${body.empleados.empleado[0].total}\r\n"
				+ "${body.empleados.empleado[1].nombres}," + 
				"${body.empleados.empleado[1].apellidos}," + 
				"${body.empleados.empleado[1].documento}," + 
				"${body.empleados.empleado[1].total}"))
		.to("mock:copy_result");
	}

}
