package ca.ece.ubc.cpen221.mp5.tests.ca.ece.ubc.cpen221.mp5.tests;

import java.io.IOException;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Client;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class ServerTests {

	@Test
	public void serverTest1() throws IOException {
		YelpDBServer server = new YelpDBServer(4949);
		Client client1 = new Client("localhost", 4949);
		server.serve();
		Client.main(new String[] {""});
		System.out.println("xd");
	}
}
