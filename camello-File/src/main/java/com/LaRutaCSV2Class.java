package com;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class LaRutaCSV2Class extends RouteBuilder{
	
	BindyCsvDataFormat mybindy = new BindyCsvDataFormat(FileCSV.class);

	@Override
	public void configure() throws Exception {
		from("file:in?noop=true")
		.split().tokenize("\n")
		.log(LoggingLevel.INFO, "-----> ${body}");
	}

}
