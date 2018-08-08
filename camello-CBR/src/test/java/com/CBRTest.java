package com;
// asserts that all the mock objects involved in this test are happy

import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CBRTest extends CamelTestSupport {
	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RutaCBR();
	} 

	@Test
	public void testWhen() throws Exception{
		MockEndpoint mockcamel = getMockEndpoint("mock:camel");
		mockcamel.expectedMessageCount(2);
		mockcamel.message(0).body().isEqualTo("Camel Rocks");
        mockcamel.message(0).header("verified").isEqualTo(true);
        mockcamel.message(0).arrives().noLaterThan(50).millis().beforeNext();
        mockcamel.message(0).simple("${header[verified]} == true");

        MockEndpoint mockOther = getMockEndpoint("mock:other");
        mockOther.expectedMessageCount(0);

        template.sendBody("direct:start", "Camel Rocks");
        template.sendBody("direct:start", "Loving the Camel");

        mockcamel.assertIsSatisfied();
        mockOther.assertIsSatisfied();

        Exchange exchange0 = mockcamel.assertExchangeReceived(0);
        Exchange exchange1 = mockcamel.assertExchangeReceived(1);
        assertEquals(exchange0.getIn().getHeader("verified"), exchange1.getIn().getHeader("verified"));
    }
	
	@Test
    public void testOther() throws Exception {
        getMockEndpoint("mock:camel").expectedMessageCount(0);
        getMockEndpoint("mock:other").expectedMessageCount(1);

        template.sendBody("direct:start", "Hello World");

        assertMockEndpointsSatisfied();
    }
	
	@Test
	public void testOnException() throws Exception{		
	
        boolean consumerStopped = false;
        try {
            template.sendBody("direct:start", "ERROR");
        } catch (CamelExecutionException e) {
            consumerStopped = true;
        }    
        assertTrue(consumerStopped);

	}
	
}
