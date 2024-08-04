package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(0);
		
	}
	
	@Test(priority = 1)
	public void createUser()
	{
		logger=LogManager.getLogger(this.getClass());
		logger.info("********** User is creating ************");
		
		Response response = UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		response.then().log().all();
		
		logger.info("********** User is created ************");
	}
	
	@Test(priority = 2)
	public void getUser()
	{
		logger.info("********** Getting user details ************");
		
		Response response = UserEndpoints.readUser(this.userPayload.getUsername());
	//	Assert.assertEquals(response.getStatusCode(),200);
		response.then().log().all();
		
		logger.info("********** User details displayed ************");
	}
	
	@Test(priority = 3)
	public void updateUser()
	{
		logger.info("********** Updating user details ************");
		
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndpoints.updateUser(userPayload, this.userPayload.getUsername());
	//	Assert.assertEquals(response.getStatusCode(),200);
		
		Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		logger.info("********** Updated user details ************");
	}
	
	@Test(priority = 4)
	public void deleteUser()
	{
		logger.info("********** Deleting user details ************");
		
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
	//	Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********** Deleted user details ************");
	}
	
	
}
