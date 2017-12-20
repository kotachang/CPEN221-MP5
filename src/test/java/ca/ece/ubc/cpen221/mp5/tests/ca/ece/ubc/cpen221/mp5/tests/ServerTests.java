package ca.ece.ubc.cpen221.mp5.tests.ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Client;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class ServerTests {

	@Test
	public void serverTest1() throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Request:");
		String request = scanner.nextLine();
		
		String reply = null;
		try {
			final YelpDBServer server = new YelpDBServer(4949);
			Thread serverThread = new Thread(()->
			{
				try {
					server.serve();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			});
			serverThread.start();
			
			Client client = new Client("localhost", 4949);
			client.sendRequest(request);
			reply = client.getReply();
			System.out.println(reply);
			
			client.close();
			
		}
		catch(IOException e) {
			fail();
		}
		scanner.close();
		System.out.println("Done");
	}
}
