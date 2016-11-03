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
import net.project.api.stats.PlayerStatsSummaryList;
import net.project.api.summoner.Summoner;

public class Converter {

	private static Gson gson;

	public Converter() {
		gson = new Gson();
	}

	/*
	 * Get some information on player given their names
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
	
	public Summoner toSummoner(String input, String region) throws CallException {
		ArrayList<String> name = new ArrayList<String>();
		name.add(input);
		Map<String, Summoner> summoner = toSummoners(name, region);
		return summoner.get(input.replace(" ", ""));
	}

	public PlayerStatsSummaryList toSummaryList(String input) {
		PlayerStatsSummaryList summaryList = gson.fromJson(input, PlayerStatsSummaryList.class);
		return summaryList;
	}
	
	public ImageIcon obtainProfileIcon(String id) {
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

}
