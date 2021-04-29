package finalproject;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import comp250.leaderboard.LeaderboardReader;

public class Leaderboard {

	public static void main(String[] args) {

		System.out.println("Retrieving data...");

		// Retrieve data
		String responseBody;
		try {
			responseBody = LeaderboardReader.getTop20Results();
		} catch (IOException e) {
			System.out.println("The results could not be retrieved.");
			return;
		}

		// Parse JSON
		JSONArray array = new JSONArray(responseBody);
		final int numResults = array.length();
		if (numResults == 0) {
			System.out.println("No scores were found");
			return;
		}
		String[] names = new String[numResults];
		int[] scores = new int[numResults];
		String[] descriptions = new String[numResults];
		int maxNameLength = 0;
		int maxScoreLength = 0;
		for (int i = 0; i < numResults; i++) {
			JSONObject obj = array.getJSONObject(i);

			names[i] = obj.getString("name");
			if (names[i].length() > maxNameLength)
				maxNameLength = names[i].length();

			scores[i] = obj.getInt("testScore");
			int len = Integer.toString(scores[i]).length();
			if (len > maxScoreLength)
				maxScoreLength = len;

			descriptions[i] = obj.getString("description");
		}

		// Display data
		int scoreWidth = Math.max(maxScoreLength, 5);
		System.out.println();
		System.out.printf("%s  %-" + maxNameLength + "s  %-" + scoreWidth
				+ "s  %s\n", "Rank", "Name", "Score", "Description");
		for (int rank = 0; rank < numResults; rank++) {
			System.out.printf("%4d  %-" + maxNameLength + "s  %" + scoreWidth
					+ "d  %s\n", rank + 1, names[rank], scores[rank],
					descriptions[rank]);
		}
	}
}
