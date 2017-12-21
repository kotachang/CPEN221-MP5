package ca.ece.ubc.cpen221.mp5.tests.ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
			server.close();

		} catch (IOException e) {
			fail();
		}
		assertEquals(reply,
				"{\"url\":\"http://www.yelp.com/user_details?userid=1\",\"votes\":\"{}\",\"review_count\":0,\"type\":\"user\",\"user_id\":\"1\",\"name\":\"New User\",\"average_stars\":0.0}");
	}

	@Test
	public void serverTest2() throws IOException, InterruptedException {

		String request = "ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/some-thai-berkeley\", \"longitude\": -122.2602981, \"neighborhoods\": [\"UC Campus Area\"], \"name\": \"Some Thai\", \"categories\": [\"Thai\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"city\": \"Berkeley\", \"full_address\": \"1995 Euclid Ave\\nUC Campus Area\\nBerkeley, CA 94709\", \"review_count\": 0, \"photo_url\": \"http://s3-media2.ak.yelpcdn.com/bphoto/1/2.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8759615, \"price\": 2}";

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		assertEquals(reply,
				"{\"open\":true,\"url\":\"http://www.yelp.com/biz/some-thai-berkeley\",\"longitude\":-122.2602981,\"neighborhoods\":[\"UC Campus Area\"],\"name\":\"Some Thai\",\"categories\":[\"Thai\",\"Restaurants\"],\"state\":\"CA\",\"type\":\"business\",\"city\":\"Berkeley\",\"full_address\":\"1995 Euclid Ave\\nUC Campus Area\\nBerkeley, CA 94709\",\"review_count\":0,\"photo_url\":\"http://s3-media2.ak.yelpcdn.com/bphoto/1/2.jpg\",\"schools\":[\"University of California at Berkeley\"],\"latitude\":37.8759615,\"price\":2,\"business_id\":\"1\",\"stars\":\"0.0\"}");
	}

	@Test
	public void serverTest3() throws IOException, InterruptedException {

		String request = "ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"text\": \"The pizza is terrible, but if you need a place to watch a game or just down some pitchers, this place works.\\n\\nOh, and the pasta is even worse than the pizza.\", \"stars\": 1, \"user_id\": \"90wm_01FAIqhcgV_mPON9Q\", \"date\": \"2017-12-20\"}";

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		assertEquals(reply,
				"{\"type\":\"review\",\"business_id\":\"1CBs84C-a-cuA3vncXVSAw\",\"votes\":{\"cool\":0,\"useful\":0,\"funny\":0},\"text\":\"The pizza is terrible, but if you need a place to watch a game or just down some pitchers, this place works.\\n\\nOh, and the pasta is even worse than the pizza.\",\"stars\":1,\"user_id\":\"90wm_01FAIqhcgV_mPON9Q\",\"date\":\"2017-12-20\",\"review_id\":\"1\"}");
	}

	@Test
	public void serverTest4() throws IOException, InterruptedException {

		String request = "GETRESTAURANT gclB3ED6uk6viWlolSb_uA";

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		assertEquals(reply,
				"{\"open\":true,\"url\":\"http://www.yelp.com/biz/cafe-3-berkeley\",\"longitude\":-122.260408,\"neighborhoods\":[\"Telegraph Ave\",\"UC Campus Area\"],\"business_id\":\"gclB3ED6uk6viWlolSb_uA\",\"name\":\"Cafe 3\",\"categories\":[\"Cafes\",\"Restaurants\"],\"state\":\"CA\",\"type\":\"business\",\"stars\":2.0,\"city\":\"Berkeley\",\"full_address\":\"2400 Durant Ave\\nTelegraph Ave\\nBerkeley, CA 94701\",\"review_count\":9,\"photo_url\":\"http://s3-media1.ak.yelpcdn.com/bphoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg\",\"schools\":[\"University of California at Berkeley\"],\"latitude\":37.867417,\"price\":1}");
	}

	@Test
	public void serverTest5() throws IOException, InterruptedException {

		String request = "GETRESTAURANT 1";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		String nullString = "null";
		assertEquals(reply, nullString);
		assertEquals(outContent.toString(),
				"client connected\r\n" + "request: GETRESTAURANT 1\r\n" + "ERR: NO_SUCH_RESTAURANT\r\n");
	}

	@Test
	public void serverTest6() throws IOException, InterruptedException {

		String request = "ADDUSER {\"nme\": \"Me\"}";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		String nullString = "null";
		assertEquals(reply, nullString);
		assertEquals(outContent.toString(),
				"client connected\r\n" + "request: ADDUSER {\"nme\": \"Me\"}\r\n" + "ERR: INVALID_USER_STRING\r\n");
	}

	@Test
	public void serverTest7() throws IOException, InterruptedException {

		String request = "ADDRESTAURANT {\"nme\": \"Me\"}";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		String nullString = "null";
		assertEquals(reply, nullString);
		assertEquals(outContent.toString(), "client connected\r\n" + "request: ADDRESTAURANT {\"nme\": \"Me\"}\r\n"
				+ "ERR: INVALID_RESTAURANT_STRING\r\n");
	}

	@Test
	public void serverTest8() throws IOException, InterruptedException {

		String request = "ADDREVIEW {\"nme\": \"Me\"}";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		String nullString = "null";
		assertEquals(reply, nullString);
		assertEquals(outContent.toString(),
				"client connected\r\n" + "request: ADDREVIEW {\"nme\": \"Me\"}\r\n" + "ERR: INVALID_REVIEW_STRING\r\n");
	}

	@Test
	public void serverTest9() throws IOException, InterruptedException {

		String request = "ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"0f8QNSVSocn40zr1tSSGRw\", \"text\": \"Food here is very consistent and good for a quick lunch or dinner. I usually order the garlic bread and salad; however, their pizza, especially the bbq chicken, is pretty good too.\", \"stars\": 4, \"user_id\": \"7\", \"date\": \"2012-01-28\"}";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		String nullString = "null";
		assertEquals(reply, nullString);
		assertEquals(outContent.toString(), "client connected\r\n"
				+ "request: ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"0f8QNSVSocn40zr1tSSGRw\", \"text\": \"Food here is very consistent and good for a quick lunch or dinner. I usually order the garlic bread and salad; however, their pizza, especially the bbq chicken, is pretty good too.\", \"stars\": 4, \"user_id\": \"7\", \"date\": \"2012-01-28\"}\r\n"
				+ "ERR: NO_SUCH_USER\r\n");
	}

	@Test
	public void serverTest10() throws IOException, InterruptedException {

		String request = "ADDREVIEW {\"type\": \"review\", \"business_id\": \"2\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"0f8QNSVSocn40zr1tSSGRw\", \"text\": \"Food here is very consistent and good for a quick lunch or dinner. I usually order the garlic bread and salad; however, their pizza, especially the bbq chicken, is pretty good too.\", \"stars\": 4, \"user_id\": \"wr3JF-LruJ9LBwQTuw7aUg\", \"date\": \"2012-01-28\"}";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		String nullString = "null";
		assertEquals(reply, nullString);
		assertEquals(outContent.toString(), "client connected\r\n"
				+ "request: ADDREVIEW {\"type\": \"review\", \"business_id\": \"2\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"0f8QNSVSocn40zr1tSSGRw\", \"text\": \"Food here is very consistent and good for a quick lunch or dinner. I usually order the garlic bread and salad; however, their pizza, especially the bbq chicken, is pretty good too.\", \"stars\": 4, \"user_id\": \"wr3JF-LruJ9LBwQTuw7aUg\", \"date\": \"2012-01-28\"}\r\n"
				+ "ERR: NO_SUCH_RESTAURANT\r\n");
	}
	
	@Test
	public void serverTest11() throws IOException, InterruptedException {

		String request = "Git gud nerd";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));

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
			server.close();

		} catch (IOException e) {
			fail();
		}
		String nullString = "";
		assertEquals(reply, nullString);
		assertEquals(outContent.toString(), "client connected\r\n"
				+ "request: Git gud nerd\r\n"
				+ "ERR: ILLEGAL_REQUEST\r\n");
	}
}
