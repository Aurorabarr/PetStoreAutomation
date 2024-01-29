package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Data drive test, data from excel data file
 */
public class DDTests {
	//public Logger logger; //create logger class
	
	//@BeforeClass
	//public void initLogger() {
		//logs
		//logger = LogManager.getLogger(this.getClass()); //initial logger
	//}
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	//create user by get data from data provider  (data driver test, excel file data file)
	public void testPostUser(String userID, String userName, String fname, String lname, String userEmail, String pwd, String phoneNum) {
		User userPayload = new User();
		
		//logger.info("****** DDTest: Create user *******");
		//logger.info(userID + " " + userName + " " + fname + " " + lname + " " + userEmail + " " + pwd + " "+ phoneNum);
		System.out.print("DDTests - testPostUser() \n"); 
		System.out.print(userID + " " + userName + " " + fname + " " + lname + " " + userEmail + " " + pwd + " "+ phoneNum +"\n");
		if(userName == null) return; //if userName is null, then do nothing
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phoneNum);
		
		Response response = UserEndPoints2.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info("******* DDTest: user created *******");
	}
	
	@Test(priority=2, dataProvider="UserName", dataProviderClass=DataProviders.class) 
	public void testGetUserByName(String userName) {
		System.out.print("DDTests - testGetUserByName() \n"); 
		System.out.print("userName: " + userName+"\n");
		if(userName == null) return; //if userName is null, then do nothing
		Response response = UserEndPoints2.readUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

	
	@Test(priority=3, dataProvider="UserName", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		//logger.info("****** DDTest: Delete user *******");
		//logger.info(userName);
		System.out.print("DDTests - testDeleteUserByName() \n"); 
		System.out.print("userName: " + userName+"\n");
		if(userName == null) return; //if userName is null, then do nothing
		Response response = UserEndPoints2.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info("****** DDTest: user deleted *******");
	}
	
}
