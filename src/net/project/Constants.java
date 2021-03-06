package net.project;

/**
 * <p>
 * Title: Constants
 * </p>
 * <p>
 * Description: This class will hold all our constants, making it easy to access
 * </p>
 * 
 * @author Justin Mintzer
 *
 */
public class Constants {

	public static final String API_KEY = new String("XXXXXXX");
	
	public static final int GET_SUMMONER_BY_NAME = 0;
	public static final int GET_RANKED_SUMMARY = 1;
	public static final int GET_CURRENT_GAME = 3;
	public static final int GET_STATIC_CHAMPION = 4;
	public static final int GET_RANKED_STATS = 5;
	
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int NOT_FOUND = 403;
	public static final int STATS_DATA_NOT_FOUND = 404;
	public static final int RATE_LIMIT_EXCEEDED = 429;
	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int SERVICE_UNAVAILABLE = 503;

	

	

	

}
