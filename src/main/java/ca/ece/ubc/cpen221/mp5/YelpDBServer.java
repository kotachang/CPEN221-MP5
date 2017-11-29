package ca.ece.ubc.cpen221.mp5;

import java.net.ServerSocket;

public class YelpDBServer implements Runnable {

	protected int serverPortVal = 4949;
	protected ServerSocket serverSocketVal = null;
	protected boolean hasStopped = false;
	protected Thread movingThread = null;

	YelpDBServer(int port) {
		this.serverPortVal = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

}
