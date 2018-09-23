package com;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class RecipientListTest extends CamelTestSupport {

	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception{
		return new MyRouteRL();
	}
	
	@Test
	public void testRecipientListProjects() throws Exception{
		String textXml = "<?xml version='1.0' encoding='UTF-8'?><estudiante><nombres>Fredis David</nombres><apellidos>Vergara Giraldo</apellidos><curso>Desarrollo web</curso><calificacion1>3.0</calificacion1><calificacion2>2.4</calificacion2><calificacion3>3.9</calificacion3></estudiante>";
		Thread.sleep(2000);
		template.sendBody("direct:filexml", textXml);
		
		MockEndpoint mockVelocity  = getMockEndpoint("mock:velocity");
		
		
		Exchange exchange = mockVelocity.assertExchangeReceived(0);
		assertEquals("<?xml version='1.0' encoding='UTF-8'?><estudiante><nombres>Fredis David</nombres><apellidos>Vergara Giraldo</apellidos><mensaje>Desarrollo web</mensaje><curso>Aprobado</curso><comentario>XXX</comentario></estudiante>", exchange.getIn().getBody());
	
	
	}
	@Test
	public void testDiferentsRLs() throws Exception{
		String textXml = "<?xml version='1.0' encoding='UTF-8'?><estudiante><nombres>Fredis David</nombres><apellidos>Vergara Giraldo</apellidos><curso>Desarrollo web</curso><calificacion1>3.0</calificacion1><calificacion2>2.4</calificacion2><calificacion3>3.9</calificacion3></estudiante>";
		Thread.sleep(2000);
		template.sendBody("direct:filexml", textXml);
		
		MockEndpoint mockLoser  = getMockEndpoint("mock:loser");
		mockLoser.expectedMessageCount(0);
		assertMockEndpointsSatisfied();
		
		MockEndpoint mockWinner  = getMockEndpoint("mock:winner");
		mockWinner.expectedMessageCount(1);
		assertMockEndpointsSatisfied();
		
	}
	
	
}
