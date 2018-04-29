import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.PayLoad;
import files.Resource;

public class Basic_rest_assured_post {
	
	 Properties prop = new Properties();
		
		@BeforeTest
		public void getData() throws IOException {
			
		
			FileInputStream fis = new FileInputStream("C:\\Users\\olatu\\eclipse-workspace\\RestAssuredAutomationAPI\\src\\files\\envr.properties");
			prop.load(fis);
		}
	
   @Test
	public void postPlace() {
		// TODO Auto-generated method stub
		
	       //BaseUrl/Host
	 		RestAssured.baseURI = prop.getProperty("HOST");
	 		
	 		
	 		given().
	 		
	 		queryParam("key", prop.getProperty("KEY")).  //use queryParam for Post method
	 		
	 		//Note: In Java, placing items in double quote into another double quote needs respective backslashes and +
	 		body(PayLoad.getPostData_body()).
	 		 //Invoking method type of "post" for resource
	 		when().
	 		post(Resource.placePostData()).
	 		
	 		 //Verify Assertions(){
		      then().
		      assertThat().statusCode(200).and(). contentType(ContentType.JSON).and().
		      body("scope", equalTo("APP")).and().
		      body("status", equalTo("OK"));
		    		  
	 		
	 		
	 		

	}

}
