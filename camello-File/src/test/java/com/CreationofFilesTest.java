package com;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CreationofFilesTest extends CamelTestSupport {
	
	@Test
	public void testCSV() throws Exception {
		template.sendBodyAndHeader("file://in", "Txt file",
				Exchange.FILE_NAME, "test.csv");
		
		Thread.sleep(1000);
		File filecsv = new File("in/test.csv");
		assertTrue("Txt not moved",filecsv.exists());

	}
	@Test
	public void testCSVcontent() throws Exception {
		MockEndpoint contentEndpoint = (MockEndpoint) context.getEndpoint("mock:contentEndpoint");
		assertTrue(contentEndpoint.message(0).toString().contains(","));
	}

}
