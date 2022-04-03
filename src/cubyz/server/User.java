package cubyz.server;

import cubyz.client.Cubyz;
import cubyz.utils.Logger;
import cubyz.utils.Zipper;
import pixelguys.json.JsonObject;
import pixelguys.json.JsonParser;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/*
*   A User
* */
public class User {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	private OutputStream outStream;
	private InputStream  inStream;

	public User(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;

		outStream = clientSocket.getOutputStream();
		inStream = clientSocket.getInputStream();

		out = new PrintWriter(outStream, true, StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8));
		
		doHandShake();
		
		while (!clientSocket.isClosed()) {
			receiveJSON(JsonParser.parseObjectFromBufferedReader(in, ""));
		}
	}
	public void receiveJSON(JsonObject json){
		String type = json.getString("type", "unknown type");
		if (type.equals("clientInformation")){
			String name     = json.getString("name", "unnamed");
			String version  =  json.getString("version", "unknown");

			Logger.info("User joined: "+name+", who is using version: "+version);
		}
	}
	
	private void doHandShake() {
		try {
            JsonObject json = JsonParser.parseObjectFromBufferedReader(in, "");
			String type = json.getString("type", "unknown type");
			if (type.equals("clientInformation")){
				String name     = json.getString("name", "unnamed");
				String version  =  json.getString("version", "unknown");
	
				Logger.info("User joined: "+name+", who is using version: "+version);
			}
			sendWorldAssets();
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	public void sendWorldAssets(){
		JsonObject head = new JsonObject();
		head.put("type","worldAssets");
		head.writeObjectToStream(out);

		String assetPath = "saves/" + Cubyz.world.getName() + "/assets/";
		Zipper.pack(assetPath,outStream); //potential bug: Stream doesnt know the file size
	}

	public void dispose() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
}
