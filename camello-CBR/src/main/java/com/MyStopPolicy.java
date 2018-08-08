package com;

import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.impl.RoutePolicySupport;

@SuppressWarnings("deprecation")
public class MyStopPolicy extends RoutePolicySupport {

@Override
public void	onExchangeBegin(Route route, Exchange exchange ) {
	String body = exchange.getIn().getBody(String.class);
	if(body.contains("ERROR")){
		try {
            stopRoute(route);
            System.out.println("Error begins ... ");
        } catch (Exception e) {
            handleException(e);
            System.out.println("Hi ! ->/t " + e);
        }
	}	
}
}
