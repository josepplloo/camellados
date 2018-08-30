package com;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public  class FileCSV implements Processor{
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Exception e = new Exception("not a CSV");
        throw new IllegalArgumentException(e);        

	}
	
	

}
