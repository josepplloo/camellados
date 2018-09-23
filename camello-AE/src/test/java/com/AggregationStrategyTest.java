package com;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class AggregationStrategyTest extends CamelTestSupport {
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception{
		return new MyRoute();
	}
	
	@Test
	public void testWhoManyMessages() throws InterruptedException {
		template.sendBody("direct:start", "2");
		template.sendBody("direct:start", "3");
		template.sendBody("direct:start", "4");
		template.sendBody("direct:start", "5");
		MockEndpoint primosmock = getMockEndpoint("mock:primos");
		primosmock.expectedMessageCount(2);
		assertMockEndpointsSatisfied();
		Exchange ex = primosmock.assertExchangeReceived(0);
		Exchange ex2 = primosmock.assertExchangeReceived(1);
		assertEquals("[4]", ex.getIn().getBody().toString());
		assertEquals("[2, 3, 5]", ex2.getIn().getBody().toString());
		assertMockEndpointsSatisfied();

	}
}
