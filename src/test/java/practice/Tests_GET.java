package practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class Tests_GET {
	
	//@Test
	public void test_01() {
		
		given()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("data.id[0]", equalTo(7));
	}

	
	//@Test
	public void test_02() {
		
		given()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("data.id[0]", equalTo(7))
			.log().all();
	}
	
	//@Test
	public void test_03() {
		
		given()
			.param("Key", "values")
			.header("key", "value")
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.log().all()
			.body("data.first_name", hasItems("Michael","Lindsay", "Tobias"));
	}
	
	@Test
	public void test_post() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		jsonMap.put("name", "Raghav");
		jsonMap.put("job", "Teacher");
		
		JSONObject request = new JSONObject(jsonMap);
		System.out.println("JSON request is : "+request);

		//JSONObject request = new JSONObject(); 

		//request.put("name", "Raghav");
		//request.put("job", "Teacher");

		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			header("Content-Type","application/json").
			body(request.toJSONString()).

		when().
			post("https://reqres.in/api/users").
		then().
			statusCode(201).
		body("", hasItems("Raghav"));
		//System.out.println(given().get("https://reqres.in/api/users").getBody().asString());
		//given().get("https://reqres.in/api/users").getBody().asString();
	}

}
