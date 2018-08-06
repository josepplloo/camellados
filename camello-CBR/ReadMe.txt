Camel Project for Blueprint 
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache Karaf.
You can run the following command from its shell:

    osgi:install -s mvn:com.mycompany/camel-blueprint/1.0.0-SNAPSHOT

For more help see the Apache Camel documentation

    http://camel.apache.org/

Consider the folow code to make a class:
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

        // asserts that all the mock objects involved in this test are happy
        assertMockEndpointsSatisfied();
    }
	
}
