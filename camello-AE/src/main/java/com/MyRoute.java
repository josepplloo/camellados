package com;


import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

public class MyRoute extends RouteBuilder {


	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("direct:start").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String number = exchange.getIn().getBody().toString();
				int body = Integer.parseInt(number);
				Boolean is=false;
				int cont = 0;
				System.out.println(body);
				for (int i = 1; i <= body ; i++) {
					if(body % i ==0)
						cont++;
						
				}
				if(cont < 3 ) {is=true; 
				}
				exchange.getIn().setHeader("primo", is);;
				
			}
		})
		.log(LoggingLevel.INFO, "HOLA ${body} y es primo ${headers.primo}")
		.aggregate(new MyAgregationStrategy()).header("primo")
		.completionTimeout(1000)
		.log(LoggingLevel.INFO, "-----> ${body}")
		.to("mock:primos");
		
		//Route from fredys --------------------

		
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(com.Artist.class);

		from("direct:artist")
	.split().tokenize("\\r\\n")
		.unmarshal(bindy)
		.log(LoggingLevel.INFO,"unmarshaling csv --> ${body}")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				Artist artista = (Artist) exchange.getIn().getBody();
				exchange.getIn().setHeader("genero", artista.getGenre());				
			}
		})
		.to("mock:bodyField")
		.aggregate(new MySecondAgregationStrategy()).header("genero").completionTimeout(1000)
		.to("velocity:artistsTemplate.vm")
		.log(LoggingLevel.INFO, "--->${body} con genero: ${headers.genero}")
		.to("mock:endPointTest");
	}

}
