package ca.ece.ubc.cpen221.mp5;

public class Client {

	YelpDBServer newServer = new YelpDBServer(4949);
	Thread thread;

	public Client() {
		thread = new Thread(newServer);
		thread.start();
	}

}
