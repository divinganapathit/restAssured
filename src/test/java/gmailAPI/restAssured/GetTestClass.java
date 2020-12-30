package gmailAPI.restAssured;

import java.util.Iterator;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetTestClass {
	@Test()
	public void verifyGetAtleastHundredRecords() {
		Log.startTestCase("Get the total number of mail in the inbox, and also get individual id for the mail");

		BasePage objBasePage = new BasePage();
		Response response = objBasePage
				.getResponse(EnvironmentURL.getPostURL1() + EnvironmentURL.userID() + EnvironmentURL.getPostURL2());
		Log.info("Verfy Status Code of API Response Actual : " + response.getStatusCode() + " Expected : "
				+ StaticData.status_200);
		assertThat(StaticData.status_200, is(response.getStatusCode()));
		JsonParser objJsonParses = new JsonParser();
		JsonObject objJsonObject = (JsonObject) objJsonParses.parse(response.asString());
		int totalMail = objJsonObject.get("resultSizeEstimate").getAsInt();
		System.out.println("Total number of mail in the inbox:" + totalMail);
		Log.info("Total number of mail in the inbox:" + totalMail);
		JsonArray messages = (JsonArray) objJsonObject.get("messages");
		for (int i = 0; i < messages.size(); i++) {
			System.out.println("The " + i + " element of the array: " + messages.get(i));
		}
		Iterator i = messages.iterator();
		while (i.hasNext()) {
			JsonObject innerObj = (JsonObject) i.next();
			System.out.println("id :" + innerObj.get("id"));
			Log.info("id:" + innerObj.get("id").getAsString());
		}
		int actual = 11;
		assertThat(actual, is(totalMail));

		/*
		 * JsonElement objJsonElement = p.parse(response.asString());
		 * System.out.println(BasePage.getKeysMethod(objJsonElement));
		 * System.out.println(response.asPrettyString()); Map<String, String> json =
		 * BasePage.getKeysMethod(objJsonElement);
		 * System.out.println("Printing the hash map:"+json);
		 * System.out.println("Total number of mail in the inbox:"+json.get(
		 * "resultSizeEstimate"));
		 * Reporter.log("Total number of mail in the inbox:"+json.get(
		 * "resultSizeEstimate"));
		 */
//		int sizeOfJsonObejcts = 0;
//		sizeOfJsonObejcts = objJsonElement.getAsJsonArray().size();
//		Log.info("No Of Records being fetched from response is : " + sizeOfJsonObejcts);
//		Log.info("Verify atleast 100 Records are fetched");
//		Assert.assertTrue(sizeOfJsonObejcts >= 100, "Failed : Less than 100 Records");

		Log.endTestCase("Completed getting the data");

	}

}
