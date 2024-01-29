package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.*;
import api.payload.User;
import io.restassured.response.Response;
import api.endpoints.UserEndPoints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests {
	Faker faker;
	User userPayload;
	
	public Logger logger; //create logger class
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass()); //initial logger
	}
	
	@Test(priority=1) 
	public void testPostUser() {
		logger.info("******* Create user *******");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******* User is created *******");
	}

	@Test(priority=2) 
	public void testGetUserByName() {
		logger.info("******* Read user *******");
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3) 
	public void testUpdateUserByName() {
		
		//update data using Payload
		logger.info("******* Update user *******");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		//responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);	
		logger.info("******* User is updated *******");
	}
	
	@Test(priority=4) 
	public void testDeleteUserByName() {
		logger.info("******* Delete user *******");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******* User is deleted *******");
	}
	
}