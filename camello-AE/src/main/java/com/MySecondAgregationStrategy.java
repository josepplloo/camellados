package com;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MySecondAgregationStrategy implements AggregationStrategy {

	@SuppressWarnings("unchecked")
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		ArrayList<Artist> list = new ArrayList<Artist>();
        Artist nartist = new Artist();
		if(oldExchange == null) {
			nartist = (Artist) newExchange.getIn().getBody();
			list.add(nartist);
			newExchange.getIn().setBody(list);
			return newExchange;
		}else {
			list = (ArrayList<Artist>) oldExchange.getIn().getBody();
			nartist = (Artist) newExchange.getIn().getBody();
			list.add(nartist);
			newExchange.getIn().setBody(list);
			return newExchange;
		}
		
		
	}

}
