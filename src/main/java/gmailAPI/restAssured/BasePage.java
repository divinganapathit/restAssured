package gmailAPI.restAssured;

import  static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;

import  io.restassured.RestAssured;
import  io.restassured.response.Response;

public class BasePage {

	public BasePage() {
		RestAssured.baseURI = EnvironmentURL.getBaseUrl();
	}

	public Response getResponse(String urls) {
		Response response = given().auth().preemptive().oauth2(EnvironmentURL.accessToken())
				.header(StaticData.contentHeader, StaticData.contentTypeJson).when().get(urls).then().extract()
				.response();
		return response;
	}

	public Response getResponseWithLogs(String urls) {
		Response response = given().auth().preemptive().oauth2(EnvironmentURL.accessToken())
				.header(StaticData.contentHeader, StaticData.contentTypeJson).log().all().when().get(urls).then().log()
				.all().extract().response();
		return response;

	}

	public static HashMap<String, String> getKeysMethod(JsonElement objJsonElement) {
		// Getting the entrySet from JsonElement

		Set<Map.Entry<String, JsonElement>> entrySet = objJsonElement.getAsJsonObject().entrySet();
		// Declaring the HashMap
		HashMap<String, String> hm = new HashMap<String, String>();

		for (Map.Entry<String, JsonElement> entry : entrySet) {
			// Checking if values is JsonObject
			if (entry.getValue().isJsonObject()) {
				// Recursive Function call
				getKeysMethod(entry.getValue());
				// Checking if values is JsonArray
			} else if (entry.getValue().isJsonArray()) {
				hm.put(entry.getKey(), entry.getValue().toString());
			} else {
				// Adding values to hashmap
				hm.put(entry.getKey(), entry.getValue().toString());
			}
		}
		return hm;
	}

	public void printOutHashMap(HashMap<String, String> hmObject) {
		Log.info("****************************************************************************");
		hmObject.entrySet().forEach(entry -> {
			Log.info("Object Key id : '" + entry.getKey() + "' - Object value is : '" + entry.getValue() + "'");
		});
		Log.info("****************************************************************************");
	}

}
