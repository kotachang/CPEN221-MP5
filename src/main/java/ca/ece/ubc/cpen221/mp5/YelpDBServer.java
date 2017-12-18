package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.json.JsonObject;
import javax.json.JsonString;


public class YelpDBServer {
	/** Default port number where the server listens for connections. */
	public static final int YelpDBServer_PORT = 4949;
	private static YelpDB database;

	private ServerSocket serverSocket;

	// Rep invariant: serverSocket != null
	//
	// Thread safety argument:
	// TODO YelpDBServer_PORT
	// TODO serverSocket
	// TODO socket objects
	// TODO readers and writers in handle()
	// TODO data in handle()

	/**
	 * Make a FibonacciServerMulti that listens for connections on port.
	 * 
	 * @param port
	 *            port number, requires 0 <= port <= 65535
	 */
	public YelpDBServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	/**
	 * Run the server, listening for connections and handling them.
	 * 
	 * @throws IOException
	 *             if the main server socket is broken
	 */
	public void serve() throws IOException {
		while (true) {
			// block until a client connects
			final Socket socket = serverSocket.accept();
			// create a new thread to handle that client
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							handle(socket);
						} finally {
							socket.close();
						}
					} catch (IOException ioe) {
						// this exception wouldn't terminate serve(),
						// since we're now on a different thread, but
						// we still need to handle it
						ioe.printStackTrace();
					}
				}
			});
			// start the thread
			handler.start();
		}
	}

	/**
	 * Handle one client connection. Returns when client disconnects.
	 * 
	 * @param socket
	 *            socket where client is connected
	 * @throws IOException
	 *             if connection encounters an error
	 */
	private void handle(Socket socket) throws IOException {
		System.err.println("client connected");

		// get the socket's input stream, and wrap converters around it
		// that convert it from a byte stream to a character stream,
		// and that buffer it so that we can read a line at a time
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		// similarly, wrap character=>bytestream converter around the
		// socket output stream, and wrap a PrintWriter around that so
		// that we have more convenient ways to write Java primitive
		// types to it.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()), true);

		try {
			// each request is a single line containing a number
			for (String line = in.readLine(); line != null; line = in
					.readLine()) {
				System.err.println("request: " + line);
				try {
					String request = "";
					if (request.equals("GETRESTAURANT")) {
						String businessID = "";
						
					}
					else if (request.equals("ADDUSER")) {
						//  {"name": "Sathish G."}
					}
					else if (request.equals("ADDRESTAURANT")) {
						
					}
					else if (request.equals("ADDREVIEW")) {
						
					}
					
					/*int x = Integer.valueOf(line);
					// compute answer and send back to client
					BigInteger y = fibonacci(x);
					System.err.println("reply: " + y);
					out.println(y);
					*/
				} catch (NumberFormatException e) {
					// complain about ill-formatted request
					System.err.println("reply: err");
					out.print("err\n");
				}
				// important! our PrintWriter is auto-flushing, but if it were
				// not:
				// out.flush();
			}
		} finally {
			out.close();
			in.close();
		}
	}

	public synchronized static String getRestaurant(String business) {
		Business find = new Business("Placeholder");
		for(Business r : database.getBusinesses()) {
			if(r.getId().equals(business)) {
				find = r;
				break;
			}
		}
		if(!find.getId().equals("Placeholder")) {
			String s = find.getJson().toString();
			return s;
		}
		return null;
	}
	public synchronized static void addUser(String user) {
		
	}
	public synchronized static void addRestarant(String restuaratnInfo) {
		
	}
	public synchronized static void addReview (String review) {
		
	}
	
	/**
	 * 
	 * Start a FibonacciServerMulti running on the default port.
	 */
	public static void main(String[] args) {
		try {
			String rest = "data/restaurants.json";
			String user = "data/users.json";
			String review = "data/reviews.json";
			YelpDBServer server = new YelpDBServer(
					YelpDBServer_PORT);
			database = new YelpDB(rest, user, review);
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

