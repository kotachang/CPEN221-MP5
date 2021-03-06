package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.json.Json;
import javax.json.JsonObject;


/**
 * @AF: A server is created with the parameter of the port where the server
 *      listens for connections. This server can handle multiple client queries
 *      at once.
 * 
 * @RI: Requires that the serverSocket is not null.
 *
 */

public class YelpDBServer extends java.lang.Thread {
	/** Default port number where the server listens for connections. */
	public static final int YelpDBServer_PORT = 4949;
	private static YelpDB database;

	private ServerSocket serverSocket;
	private static int userIds = 1;
	private static int restuarantIds = 1;
	private static int reviewIds = 1;

	private volatile boolean isRunning;

	// Rep invariant: serverSocket != null

	/**
	 * Make a YelpDBServer that listens for connections on port.
	 * 
	 * @param port
	 *            port number, requires 0 <= port <= 65535
	 */
	public YelpDBServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		database = new YelpDB(rest, user, review);
	}

	/**
	 * Run the server, listening for connections and handling them.
	 * 
	 * @throws IOException
	 *             if the main server socket is broken
	 */
	public void serve() throws IOException {
		isRunning = true;
		while (isRunning) {
			// block until a client connects
			try {
				final Socket socket = serverSocket.accept();
				// create a new thread to handle that client
				Thread handler = new Thread(new Runnable() {
					public void run() {
						try {
							try {
								handle(socket);
							} finally {
								isRunning = false;
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
			} catch (SocketException e) {
				break;
			}
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
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// similarly, wrap character=>bytestream converter around the
		// socket output stream, and wrap a PrintWriter around that so
		// that we have more convenient ways to write Java primitive
		// types to it.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			// each request is a single line containing a number
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);
				try {
					line = line.trim();
					String[] request = new String[2];
					request[0] = line.substring(0, line.indexOf(' '));
					request[1] = line.substring(line.indexOf(' ') + 1);
					if (request[0].equals("GETRESTAURANT")) {
						String business = YelpDBServer.getRestaurant(request[1]);
						out.println(business);
						out.flush();
						line = null;
					} else if (request[0].equals("ADDUSER")) {
						out.println(YelpDBServer.addUser(request[1]));
						out.flush();
						line = null;
					} else if (request[0].equals("ADDRESTAURANT")) {
						out.println(YelpDBServer.addRestarant(request[1]));
						out.flush();
						line = null;
					} else if (request[0].equals("ADDREVIEW")) {
						out.println(YelpDBServer.addReview(request[1]));
						out.flush();
						line = null;
					} else {
						System.err.println("ERR: ILLEGAL_REQUEST");
						out.println("");
						out.flush();
						line = null;
					}

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

	/**
	 * 
	 * @param business
	 *            String representing the business' ID
	 * 
	 * @return string of the restaurants information in JSON format, or null if the
	 *         restaurant is not in the database.
	 */
	public synchronized static String getRestaurant(String business) {
		Business find = new Business("Placeholder");
		for (Business r : database.getBusinesses()) {
			if (r.getId().equals(business)) {
				find = r;
				break;
			}
		}
		if (!find.getId().equals("Placeholder")) {
			String s = find.getJson().toString();
			return s;
		}
		System.err.println("ERR: NO_SUCH_RESTAURANT");
		return null;
	}

	/**
	 * 
	 * @param user
	 *            string representing the user's name in JSON format. ex. {"name":
	 *            "Me"}
	 * @return a string of the added user's information in JSON format, or null if
	 *         the string is incorrectly formated.
	 */
	public synchronized static String addUser(String user) {
		JsonObject newUser = Json.createReader(new StringReader(user)).readObject();
		User addUser = new User("" + userIds);
		try {
			addUser.setName(newUser.getString("name"));
			userIds++;
			addUser.setURL("http://www.yelp.com/user_details?userid=" + addUser.getId());
			database.addUser(addUser);
			return addUser.toString();
		} catch (NullPointerException e) {
			System.err.println("ERR: INVALID_USER_STRING");
		}
		return null;
	}

	/**
	 * 
	 * @param restaurantInfo
	 *            a string representing the restaurant's information in JSON format
	 *            (excluding the businessId)
	 * @return a string of the added restaurant's information in JSON format, or
	 *         null if the string is incorrectly formated
	 */
	public synchronized static String addRestarant(String restaurantInfo) {
		JsonObject restaurantData = Json.createReader(new StringReader(restaurantInfo)).readObject();
		try {
			restaurantData = Json.createObjectBuilder(restaurantData).add("business_id", "" + restuarantIds)
					.add("stars", "0.0").build();
			restuarantIds++;
			Restaurant newRestaurant = (Restaurant) database.parseBusiness(restaurantData);
			database.addBusiness(newRestaurant);
			return newRestaurant.getJson().toString();
		} catch (NullPointerException e) {
			System.err.println("ERR: INVALID_RESTAURANT_STRING");
		}
		return null;
	}

	/**
	 * 
	 * @param review
	 *            a string representing the review's information in JSON format
	 * @return a string of the added review's information in JSON format, or null if
	 *         the string is incorrectly formated, or the database does not contain
	 *         the user or the restaurant associated with the review.
	 */
	public synchronized static String addReview(String review) {
		JsonObject reviewData = Json.createReader(new StringReader(review)).readObject();
		try {
			reviewData = Json.createObjectBuilder(reviewData).add("review_id", "" + reviewIds).build();
			reviewIds++;
			Review newReview = database.parseReview(reviewData);
			if (newReview == null) {
				String s = reviewData.getString("user_id");
				if (!database.getUsers().contains(new User(s))) {
					System.err.println("ERR: NO_SUCH_USER");
				}
				s = reviewData.getString("business_id");
				if (!database.getBusinesses().contains(new Business(s))) {
					System.err.println("ERR: NO_SUCH_RESTAURANT");
				}
				return null;
			} else {
				database.addReview(newReview);
				return newReview.getJson().toString();
			}

		} catch (NullPointerException e) {
			System.err.println("ERR: INVALID_REVIEW_STRING");
		}
		return null;
	}

	/**
	 * 
	 * Start a YelpDBServer running on the default port.
	 */
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(YelpDBServer_PORT);
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			this.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the current Server socket.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void close() throws InterruptedException, IOException {
		isRunning = false;
		serverSocket.close();
	}

}
