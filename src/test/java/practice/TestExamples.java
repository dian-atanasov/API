package practice;



import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestExamples {

	@Test
	public void test_01() {
		//import io.restassured.RestAssured;
		//Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		
		//import static io.restassured.RestAssured.*;
		Response response = get("https://reqres.in/api/users?page=2");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getHeader("content-type"));
		System.out.println(response.getStatusLine());
		
		int responseCode = response.getStatusCode();
		
		Assert.assertEquals(responseCode, 200);
	}
	
	@Test
	public void test_02() {
		
		given().
			get("https://reqres.in/api/users?page=2").
		then().
			statusCode(200).
			body("data.id[0]", equalTo(7));
		
	}
}
