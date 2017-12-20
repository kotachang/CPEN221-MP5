package ca.ece.ubc.cpen221.mp5.tests.ca.ece.ubc.cpen221.mp5.tests;

import java.io.IOException;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Client;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class ServerTests {

	@Test
	public void serverTest1() throws IOException {
		Thread server = new YelpDBServer(4949);
		Thread client = new Client("localhost",4949);
		client.run();
		server.run();
		System.out.println("Finsihed");
	}
}
