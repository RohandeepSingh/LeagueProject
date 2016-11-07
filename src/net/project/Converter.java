package net.project;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.project.api.CallException;
import net.project.api.CallRiot;
import net.project.api.currentgame.CurrentGameInfo;
import net.project.api.staticdata.Champion;
import net.project.api.stats.AggregatedStats;
import net.project.api.stats.PlayerStatsSummary;
import net.project.api.stats.PlayerStatsSummaryList;
import net.project.api.stats.RankedStats;
import net.project.api.summoner.Summoner;

public class Converter {

	private static Gson gson;

	public Converter() {
		gson = new Gson();
	}

	/*
	 * This method takes in a list of players and the region. It will call riot,
	 * get the json strings and convert it using gson into a Summoner object
	 * 
	 * @return a map of players
	 */
	public Map<String, Summoner> toSummoners(List<String> name, String region) throws CallException {
		CallRiot call = new CallRiot();
		call.setRegion(region);
		String names = new String("");
		for (String i : name) {
			names = names + i + ",";
		}

		call.modifyURL(Constants.GET_SUMMONER_BY_NAME, names);
		String json = call.now();
		Map<String, Summoner> summoner = gson.fromJson(json, new TypeToken<Map<String, Summoner>>() {
		}.getType());
		return summoner;
	}

	/**
	 * This method takes in a name of a summoner and the region. It will return
	 * a single summoner object using toSummoners method.
	 * 
	 * @param input
	 *            - name of summoner you want
	 * @param region
	 *            - the region
	 * @return
	 * @throws CallException
	 *             if we cant find the summoner or whatever else can go wrong in
	 *             a call
	 */
	public Summoner getSummoner(String input, String region) throws CallException {
		ArrayList<String> name = new ArrayList<String>();
		name.add(input);
		Map<String, Summoner> summoner = toSummoners(name, region);
		return summoner.get(input.replace(" ", ""));
	}

	/**
	 * This method will convert a json string of a PlayerStatsSummaryList and
	 * store into an object using gson
	 * 
	 * @param input
	 * @return a PlayerStatsSummaryList object
	 */
	public PlayerStatsSummaryList toSummaryList(String json) {
		PlayerStatsSummaryList summaryList = gson.fromJson(json, PlayerStatsSummaryList.class);
		return summaryList;
	}

	/**
	 * This method will convert a json string of a CurrentGameInfo object and
	 * store it into a java object
	 * 
	 * @param json
	 * @return CurrentGameInfo object
	 */
	public CurrentGameInfo getCurrentGameInfo(String json) {
		CurrentGameInfo cgi = gson.fromJson(json, CurrentGameInfo.class);
		return cgi;
	}

	/**
	 * This method will take in a profileiconid
	 * 
	 * @param id
	 * @return
	 */
	public ImageIcon obtainProfileIcon(long id) {
		ImageIcon icon = null;
		try {
			URL url = new URL("http://ddragon.leagueoflegends.com/cdn/6.21.1/img/profileicon/" + id + ".png");
			BufferedImage image = ImageIO.read(url);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}

	/**
	 * This method will take in a json string and create/return a champion
	 * object from it using gson
	 * 
	 * @param json
	 * @return
	 */
	public Champion getChampion(String json) {
		return gson.fromJson(json, Champion.class);
	}

	public RankedStats getRankedStats(String json) {
		return  gson.fromJson(json, RankedStats.class);
	}
	
	public PlayerStatsSummary getPlayerStatsSummary(String json) {
		return gson.fromJson(json, PlayerStatsSummary.class);
	}
	
	public PlayerStatsSummaryList getPlayerStatsSummaryList(String json) {
		return gson.fromJson(json, PlayerStatsSummaryList.class);
	}

}
