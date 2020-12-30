package gmailAPI.restAssured;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;

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

}
