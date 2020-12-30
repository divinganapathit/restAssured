package gmailAPI.restAssured;

import java.util.ResourceBundle;

public class EnvironmentURL {
	static ResourceBundle userCreditinals = ResourceBundle.getBundle("credentials");

	public static String getBaseUrl() {
		Log.info("BASE URL : " + userCreditinals.getString("baseURL"));
		return userCreditinals.getString("baseURL");
	}

	public static String getPostURL1() {
		Log.info("Append POST URL : " + userCreditinals.getString("listPart1"));
		return userCreditinals.getString("listPart1");
	}

	public static String getPostURL2() {
		Log.info("Append POST URL : " + userCreditinals.getString("listPart2"));
		return userCreditinals.getString("listPart2");
	}

	public static String userID() {
		Log.info("Append UserID : " + userCreditinals.getString("userID"));
		return userCreditinals.getString("userID");
	}

	public static String accessToken() {
		return userCreditinals.getString("access_Token");
	}

}
