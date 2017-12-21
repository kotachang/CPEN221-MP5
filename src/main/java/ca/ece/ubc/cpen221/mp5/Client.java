package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @AF: A client is created with parameters of the hostname of the client, and
 *      the port that it connects to the YelpDBServer through. This client can
 *      send requests to a server and get replies from the server. The client is
 *      "open" until the "close" method is called.
 * 
 * @RI: Requires that the "socket" is not null, "in" is not null, "out" is not
 *      null.
 * 
 */

public class Client extends java.lang.Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Make a Client and connect it to a server running on hostname at the specified
	 * port.
	 * 
	 * @throws IOException
	 *             if can't connect
	 * @throws UnkownHostException
	 *             if the host cannot be determined
	 */
	public Client(String hostname, int port) throws UnknownHostException, IOException {
		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	/**
	 * Send a request to the server. Requires this is "open".
	 * 
	 * @param request
	 *            is the request sent to the server, which will be a string formated
	 *            as: {QUERY} {INFORMATION}, ex. ADDUSER {"name": "Me"}
	 */
	public void sendRequest(String request) {
		out.println(request);
		out.flush();
	}

	/**
	 * Get a reply from the next request that was submitted. Requires this is
	 * "open".
	 * 
	 * @return the requested information
	 * @throws IOException
	 *             if network or server failure
	 */
	public String getReply() throws IOException {
		String reply = in.readLine();
		if (reply == null) {
			throw new IOException("connection terminated unexpectedly");
		}
		return reply;
	}

	/**
	 * Closes the client's connection to the server. This client is now "closed".
	 * Requires this is "open".
	 * 
	 * @throws IOException
	 *             if close fails
	 */
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			Client client = new Client("localhost", 4949);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Request:");
			String request = scanner.next();

			client.sendRequest(request);
			System.out.println(client.getReply());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Request:");
			String request = scanner.nextLine();

			this.sendRequest(request);
			System.out.println(this.getReply());
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
