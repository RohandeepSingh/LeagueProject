package net.project.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import net.project.Constants;

/**
 * <p>
 * Title: Call Riot class
 * </p>
 * <p>
 * Description: This class will be used to call riot and store whatever they
 * reply with
 * </p>
 * 
 * @author Justin Mintzer
 *
 */
public class CallRiot {

	private String url;
	private String region;
	private String response;

	/**
	 * Creates the base for our URL
	 */
	public CallRiot() {
		url = new String();
		region = new String();
		response = new String();
	}

	public String now() throws CallException {
		try {
			if (!url.contains("dragon"))
				url += "api_key=" + Constants.API_KEY;

			System.out.println(url);
			URL url = new URL(this.url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (conn.getResponseCode() != 200)
				throw new CallException(conn.getResponseCode());
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			response = new String(buffer.toString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	/**
	 * The region we are calling about
	 * 
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * The method is the url with different attributes. Different methods can
	 * alter the reply of riot
	 * 
	 * @param method
	 * @param args
	 */
	public void modifyURL(int callValue, String id) {
		url = "https://" + region + ".api.pvp.net/api/lol/" + region + "/";
		if (!id.isEmpty()) {

			switch (callValue) {

			case Constants.GET_SUMMONER_BY_NAME:
				try {
					url += "v1.4/summoner/by-name/" + URLEncoder.encode(id, "UTF-8").replace("+", "%20") + "?";
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				break;

			case Constants.GET_STATS_SUMMARY:
				url += "v1.3/stats/by-summoner/" + id + "/summary?season=SEASON2016&";
				break;

			}
		}

	}

}
