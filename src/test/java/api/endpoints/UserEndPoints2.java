package api.endpoints;

import static io.restassured.RestAssured.given;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ResourceBundle;

/*
 * UserEndPoint2.java : using property file to get URLs 
 * Perform Create, Update, Read and delete requests to the user API
*/
public class UserEndPoints2 {
	
	//Load properties file ("routes" is the name of property file , route is a variable name)
	static ResourceBundle getURL() {
		ResourceBundle route = ResourceBundle.getBundle("routes");
		return route;
	}
	
	public static Response createUser(User payload) {
		String post_url = getURL().getString("post_url");
		Response response = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when().post(post_url);
		return response;
	}
		
	public static Response readUser(String username) {
		String get_url = getURL().getString("get_url");
		Response response = given()
					.pathParam("username", username)
				.when().get(get_url);
		return response;
	}
	
	public static Response updateUser(String username, User payload){
		String update_url = getURL().getString("update_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
			.when().post(update_url);
		return response;
	}
	
	public static Response deleteUser(String username) {
		System.out.print("UserPoint2 - deleteUser, username: " + username + "\n");
		String delete_url = getURL().getString("delete_url");
		Response response = given()
					.pathParam("username", username)
				.when().delete(delete_url);
		return response;
	}

}
