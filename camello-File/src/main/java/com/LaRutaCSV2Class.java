package com;



import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

public class LaRutaCSV2Class extends RouteBuilder{
	final CsvDataFormat hicsv = new CsvDataFormat();
	
	
	@Override
	public void configure() throws Exception {
        errorHandler(deadLetterChannel("seda:errors"));		
		hicsv.setIgnoreEmptyLines(true);
		from("file:in?noop=true")
		.unmarshal(hicsv)
		.log(LoggingLevel.INFO, "${routeId} -----> ${body}")
		.to("direct:contentEndpoint");
		
        Processor myFileprocess = new FileCSV();
		
		from("direct:contentEndpoint")
		.filter(body().convertToString().not().contains(","))
		.process(myFileprocess)
		.log(LoggingLevel.INFO, "${routeId} : ${exception} -----> ${body}")
		.to("mock:contentEndpoint");
		
	}

}
