import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import files.PayLoad;
import files.Resource;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basic_rest_assured_add_delete {
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		
	
		FileInputStream fis = new FileInputStream("C:\\Users\\olatu\\eclipse-workspace\\RestAssuredAutomationAPI\\src\\files\\envr.properties");
		prop.load(fis);
		
		//prop.get("HOST");
		
	}

	
	@Test
	public void add_delete() {
		
		
		// Creating String to store value and call the variable "a" of the string later in the code
		
		
		
		//Task1 to grab response>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
 		RestAssured.baseURI = prop.getProperty("HOST");
 		
 		
 		Response res = given().
 		
 		queryParam("key", prop.getProperty("KEY")).  //use queryParam for Post method
 		
 		//Note: In Java, placing items in double quote into another double quote needs respective backslashes and +
 		body(PayLoad.getPostData_body()).
 		
 		 //Invoking method type of "post" for resource
 		when().
 		post(Resource.placeAddData()).
 		
 		 //Verify Assertions(){
	      then().
	      assertThat().statusCode(200).and(). contentType(ContentType.JSON).and().
	      body("scope", equalTo("APP")).and().
	      body("status", equalTo("OK")).and().
	      extract().response();
 		
 		 System.out.println("The new place has now been added");
 		 
 		 String responseString = res.asString();
 		 
 		 System.out.println("This is the response in string format"   +  responseString);
 		 
 		
 		 
 		 
 		 //Task2 To grab "Place ID" from the response:>>>>>>>>>>>>>>>
 		 
 		//>Converting responseString to Json >>>>>
 		 
 		 JsonPath jsn = new JsonPath(responseString);  
 		 String PlaceId = jsn.get("place_id");
 		 System.out.println("This is the place id in Json format:  "  + PlaceId);
 		 
 		 
 		//Task3 To place the "Place_id" value into delete request>>>>>>>>>>>>>
 		 
 		 given().
 		 queryParam("key", "AIzaSyCvBNc5X_C8MBoLFJo1kdbgCMR9snHMf54").
 		 
 		 body("{\r\n" + 
 		 		"  \"place_id\": \""+PlaceId+"\""+ 
 		 		"}").
 		 when().
 		 post("/maps/api/place/delete/json").     //Pass resource
 		 
 		 then().
 		 assertThat().statusCode(200).and(). 
 		 contentType(ContentType.JSON).and().
	     body("status", equalTo("OK"));
 		 
 		 System.out.println("This place id has finally been deleted.... "  + "OK!");
	    		  
	}

}
