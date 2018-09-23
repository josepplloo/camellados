package com;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAgregationStrategy implements AggregationStrategy {


	
	@SuppressWarnings({ "unchecked" })
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        ArrayList<String> list = new ArrayList<String>();
        String nbody ="";
		System.out.println("AS ->"+newExchange.getIn().getBody().toString());
        if(oldExchange == null) {
            nbody = newExchange.getIn().getBody().toString();
            list.add(nbody);
            newExchange.getIn().setBody(list);
        	return newExchange;
        }else {
        	list = (ArrayList<String>) oldExchange.getIn().getBody();
            String obody = newExchange.getIn().getBody().toString();
            list.add(obody);
            newExchange.getIn().setBody(list);
        	return newExchange;

        }
	}	

	

}
