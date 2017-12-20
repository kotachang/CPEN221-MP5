package ca.ece.ubc.cpen221.mp5.tests.ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Client;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class ServerTests {

	@Test
	public void serverTest1() throws IOException, InterruptedException {

		String request = "ADDUSER {\"name\": \"New User\"}";

		String reply = null;
		try {
			final YelpDBServer server = new YelpDBServer(4949);
			Thread serverThread = new Thread(() -> {
				try {
					server.serve();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			serverThread.start();

			Client client = new Client("localhost", 4949);
			client.sendRequest(request);
			reply = client.getReply();
			
			client.close();

		} catch (IOException e) {
			fail();
		}
		assertEquals(reply, "{\"url\":\"http://www.yelp.com/user_details?userid=1\",\"votes\":\"{}\",\"review_count\":0,\"type\":\"user\",\"user_id\":\"1\",\"name\":\"New User\",\"average_stars\":0.0}");
	}
}
