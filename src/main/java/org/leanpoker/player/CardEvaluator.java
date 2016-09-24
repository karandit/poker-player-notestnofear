package org.leanpoker.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CardEvaluator {
	
	
	public void evaluate(List<Card> cards) {
		
		
	}
	
	public void createRequest(List<Card> cards) throws IOException {
		URL url = new URL("http://rainman.leanpoker.org/rank");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
//		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("user-agent", "MyUser");
		connection.setRequestMethod("POST");
		
		
		OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
		wr.write(createRequestParam(cards).toString());
		wr.flush();
		wr.close();
		
		InputStream is = connection.getInputStream();
		byte[] buf = new byte[connection.getContentLength()];
		IOUtils.read(is, buf);
		String string = IOUtils.toString(buf);
		System.out.println(string);
	}
	
	private JsonObject createRequestParam(List<Card> cards) {
		JsonObject cardsJson = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		
		for(Card card : cards) {
			JsonObject cardJson = new JsonObject();
			cardJson.addProperty("rank", card.getRank());
			cardJson.addProperty("suit", card.getSuit());
			jsonArray.add(cardJson);
			
		}
		cardsJson.add("cards", jsonArray);
		System.out.println(cardsJson.toString());
		return cardsJson;
	}

}
