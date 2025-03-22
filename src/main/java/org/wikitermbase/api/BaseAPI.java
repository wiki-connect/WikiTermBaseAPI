package org.wikitermbase.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wikitermbase.api.DataModel.Group;
import org.wikitermbase.api.DataModel.Occurence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class BaseAPI {

	private static final String BASE_URL = "https://wikitermbase.toolforge.org";

	/**
	 * Executes an HTTP GET request and returns the response as a string.
	 *
	 * @param urlString The URL to send the request to.
	 * @return The response body as a string.
	 * @throws IOException If an I/O error occurs.
	 */
	protected String executeRequest(String urlString) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		connection.setConnectTimeout(30000);
		connection.setReadTimeout(30000);

		int responseCode = connection.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK) {
			throw new IOException("Unexpected response status: " + responseCode);
		}

		InputStream inputStream = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}
		return response.toString();

	}

	/**
	 * Searches for aggregated term groups.
	 *
	 * @param query The search query.
	 * @return An array of Group objects.
	 * @throws IOException If an I/O error occurs.
	 * @throws JSONException If JSON parsing fails.
	 */
	public Group[] searchAggregated(String query) throws IOException, JSONException {
		String url = buildUrl("/api/v1/search/aggregated", query);
		return parseJsonToGroups(executeRequest(url));
	}

	/**
	 * Searches for individual occurrences of terms.
	 *
	 * @param query The search query.
	 * @return An array of Occurence objects.
	 * @throws IOException If an I/O error occurs.
	 * @throws JSONException If JSON parsing fails.
	 */
	public Occurence[] search(String query) throws IOException, JSONException {
		String url = buildUrl("/api/v1/search", query);
		return parseJsonToOccurences(executeRequest(url));
	}

	/**
	 * Builds a URL with the given path and query parameter.
	 *
	 * @param path The API path.
	 * @param query The search query.
	 * @return A constructed URL as a string.
	 */
	private String buildUrl(String path, String query) {
		return BASE_URL + path + "?q=" + query;
	}

	private Group[] parseJsonToGroups(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONArray groupsArray = jsonObject.optJSONArray("groups");
		if (groupsArray == null)
			return new Group[0];

		Group[] groups = new Group[groupsArray.length()];
		for (int i = 0; i < groupsArray.length(); i++) {
			groups[i] = parseGroup(groupsArray.getJSONObject(i));
		}
		return groups;
	}

	private Occurence[] parseJsonToOccurences(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONArray results = jsonObject.optJSONArray("results");
		return parseOccurences(results);
	}

	private Group parseGroup(JSONObject groupJson) throws JSONException {
		return new Group(groupJson.optString("arabic_normalised", null),
				parseDictionaryIds(groupJson.optJSONArray("dictionary_ids")),
				groupJson.optString("english_normalised", null), groupJson.optString("french_normalised", null),
				parseOccurences(groupJson.optJSONArray("occurences")), groupJson.optDouble("total_relevance", 0.0));
	}

	private long[] parseDictionaryIds(JSONArray dictionaryIdsArray) {
		if (dictionaryIdsArray == null)
			return new long[0];
		long[] dictionaryIds = new long[dictionaryIdsArray.length()];
		for (int i = 0; i < dictionaryIdsArray.length(); i++) {
			dictionaryIds[i] = dictionaryIdsArray.optLong(i, 0);
		}
		return dictionaryIds;
	}

	private Occurence[] parseOccurences(JSONArray occurencesArray) throws JSONException {
		if (occurencesArray == null)
			return new Occurence[0];
		Occurence[] occurences = new Occurence[occurencesArray.length()];
		for (int i = 0; i < occurencesArray.length(); i++) {
			JSONObject occurenceJson = occurencesArray.getJSONObject(i);
			occurences[i] = new Occurence(occurenceJson.optString("arabic", null),
					occurenceJson.optString("arabic_normalised", null), occurenceJson.optInt("dictionary_id", 0),
					occurenceJson.optString("dictionary_name_arabic", null),
					occurenceJson.optString("dictionary_wikidata_id", null), occurenceJson.optString("english", null),
					occurenceJson.optString("french", null), occurenceJson.optInt("id", 0),
					occurenceJson.optDouble("relevance", 0.0), occurenceJson.optString("uri", null));
		}
		return occurences;
	}
}
