package realstate.api.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sun.jersey.api.client.Client;

/**
 * @author giuseppe
 * IT stands for "Integration Test"
 */
public class PropertyServiceIT {
	@Test
	public void find() {
		Client client = Client.create();
		String actual = 
				client
					.resource("http://localhost:8080/properties/13")
					.get(String.class);
		assertEquals("{\"id\":13,\"price\":0,\"x\":79,\"y\":305,\"beds\":2,\"baths\":1,\"provinces\":[\"Scavy\"],\"squareMeters\":45}", actual);
	}

}
