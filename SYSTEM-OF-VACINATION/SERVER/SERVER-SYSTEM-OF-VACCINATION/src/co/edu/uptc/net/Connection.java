package co.edu.uptc.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

public class Connection {
	private Socket client;
	private DataInputStream input;
	private DataOutputStream output;
	private Gson myGson;
	
	public Connection(Socket client) throws IOException{
		this.myGson = new Gson();
		this.client = client;
		this.input = new DataInputStream(this.client.getInputStream());
		this.output = new DataOutputStream(this.client.getOutputStream());
	}
	
	public Socket getClient() {
		return client;
	}
	
	public DataInputStream getInput() {
		return input;
	}
	
	public DataOutputStream getOutput() {
		return output;
	}
	
	public Gson getMyGson() {
		return myGson;
	}
}
