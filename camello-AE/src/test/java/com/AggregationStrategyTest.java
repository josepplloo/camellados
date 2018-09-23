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
	
	// powered by https://github.com/fdvergara/Camel-Development-exercises/tree/master/camel-aggregator
	
		@Test
		public void testDirect() throws InterruptedException {
			template.sendBody("direct:artist", "Alternative,The White Stripes,Elephant,Get Behind Me Satan,White Blood Cells\r\n" + 
					"Classic,Paciencia,Boarding House Reach,Reluciente,Man of The Woods\r\n" + 
					"Alternative,Rompecabezas,Centésimo Humano,Music for cars,eMOTIVe\r\n" + 
					"Classic,Tres canciones,La locura,Titulo de amore,Mi vida musical");
			
			Thread.sleep(2000);
			
			MockEndpoint bodyField = getMockEndpoint("mock:bodyField");		
			bodyField.message(2).header("genero").isEqualTo("Alternative");
			
			MockEndpoint endPointTest = getMockEndpoint("mock:endPointTest");
			endPointTest.expectedMessageCount(2);
			endPointTest.assertIsSatisfied();
			
			Exchange endPointTest_ex1 = endPointTest.assertExchangeReceived(0);
			Exchange endPointTest_ex2 = endPointTest.assertExchangeReceived(1);
			
			assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
					"<Artists>\r\n" + 
					"<artist genre='Alternative'>\r\n" + 
					" <name>The White Stripes</name>\r\n" + 
					" <album>Elephant</album>\r\n" + 
					" <album>Get Behind Me Satan</album>\r\n" + 
					" <album>White Blood Cells</album>\r\n" + 
					"</artist>	\r\n" + 
					"<artist genre='Alternative'>\r\n" + 
					" <name>Rompecabezas</name>\r\n" + 
					" <album>Centésimo Humano</album>\r\n" + 
					" <album>Music for cars</album>\r\n" + 
					" <album>eMOTIVe</album>\r\n" + 
					"</artist>	\r\n" + 
					"<Artists>", endPointTest_ex1.getIn().getBody());
			
			assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
					"<Artists>\r\n" + 
					"<artist genre='Classic'>\r\n" + 
					" <name>Paciencia</name>\r\n" + 
					" <album>Boarding House Reach</album>\r\n" + 
					" <album>Reluciente</album>\r\n" + 
					" <album>Man of The Woods</album>\r\n" + 
					"</artist>	\r\n" + 
					"<artist genre='Classic'>\r\n" + 
					" <name>Tres canciones</name>\r\n" + 
					" <album>La locura</album>\r\n" + 
					" <album>Titulo de amore</album>\r\n" + 
					" <album>Mi vida musical</album>\r\n" + 
					"</artist>	\r\n" + 
					"<Artists>", endPointTest_ex2.getIn().getBody());
		}
}
