package net.project;

import net.project.api.CallException;
import net.project.api.CallRiot;
import net.project.api.stats.AggregatedStats;
import net.project.api.stats.RankedStats;

/**
 * Used for testing stuff
 * 
 * @author Justin Mintzer
 *
 */
public class Test {

	public static void main(String[] args) {
		CallRiot call = new CallRiot();
		call.setRegion("NA");

		Converter converter = new Converter();
		try {
			call.modifyURL(Constants.GET_RANKED_STATS, "32925658");
			RankedStats rankedStats = converter.getRankedStats(call.now());
			for (int i = 0; i < rankedStats.getChampions().size(); i++) {
				if (rankedStats.getChampions().get(i).getId() == 0) {
					AggregatedStats allStats = rankedStats.getChampions().get(i).getStats();
					System.out.println("WINS: " + allStats.getTotalSessionsWon());
					System.out.println("LOSSES: " + allStats.getTotalSessionsLost());
				}
			}
			//System.out.println(rankedStats.getChampions().get(i));
			

		} catch (CallException e) {
			System.out.println(e.getMessage());
		}
	}

}
