package api.endpoints;

/* 
 * Swagger URL: https://petstore.swagger.io/
 * Create user (Post): https://petstore.swagger.io/v2/user
 * Get user (Get): https://petstore.swagger.io/v2/user/{username}
 * Update user (Put) https://petstore.swagger.io/user/{username}
 * Delete user (Delete): https://petstore.swagger.io/user/{username}
 */

public class Routes {
	//public static String base_url = "https://petstore.swagger.io/v2";
	public static String base_url = "https://petstore3.swagger.io/api/v3/";
	
	//User module
	public static String post_url = base_url +"user";
	public static String get_url = base_url + "user/{username}";
	public static String update_url = base_url + "user/{username}";
	public static String delete_url = base_url + "user/{username}";
	
	//Store module
	  //here we create store URLs
	//Pet module
	  //here we create pet URLs

}

