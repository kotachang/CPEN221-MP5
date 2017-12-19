package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Client(String hostname, int port) throws UnknownHostException, IOException {
		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	public void sendRequest(String request) {
		out.print(request);
		out.flush();
	}
	
	public String getReply() throws IOException {
		String reply = in.readLine();
		if(reply == null) {
			throw new IOException("connection terminated unexpectedly");
		}
		return reply;
	}
	
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
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
