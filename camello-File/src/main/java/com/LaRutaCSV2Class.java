package com;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

public class LaRutaCSV2Class extends RouteBuilder{
	final CsvDataFormat hicsv = new CsvDataFormat();
	
	
	@Override
	public void configure() throws Exception {
		hicsv.setIgnoreEmptyLines(true);
		from("file:in?noop=true")
		.unmarshal(hicsv)
		.log(LoggingLevel.INFO, "-----> ${body}");
		
		/*
		 * we can define a second route for process the 
		 * file and generate a bean or an error
		 * fix cv
		*/
		
	}

}
