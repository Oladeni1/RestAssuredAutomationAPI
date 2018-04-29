import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Resource;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class Basic_rest_assured_get {
	
    Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		
	
		FileInputStream fis = new FileInputStream("C:\\Users\\olatu\\eclipse-workspace\\RestAssuredAutomationAPI\\src\\files\\envr.properties");
		prop.load(fis);
	}
	@Test
	public void searchPlace(){
		
		//BaseUrl/Host
		RestAssured.baseURI =prop.getProperty("HOST");
		
		
		//Add Parameters + API key
		given().
		      param("location", "-33.8670522,151.1957362").
		      param("radius", "1500").
		      param("key", prop.getProperty("KEY")).
		      
		      //Invoking method type of "get" for resource
		      when().
		      get(Resource.placeSearchData()).
		      
		      
		      //Verify Assertions(){
		      then().
		      assertThat().statusCode(200).and().
		      header("Server", "scaffolding on HTTPServer2").and().
		      contentType(ContentType.JSON).and().
		      body("results[0].name",equalTo("Sydney")).and().
		      body("results[0].scope",equalTo("GOOGLE"));
		
		 System.out.println("The http status code is" + " 200");
		 
	}

}
