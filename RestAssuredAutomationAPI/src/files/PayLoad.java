package files;

public class PayLoad{
	
	public static String getPostData_body() {
		
		String a ="{\r\n" + 
 				"  \"location\": {\r\n" + 
 				"    \"lat\": -33.8669710,\r\n" + 
 				"    \"lng\": 151.1958750\r\n" + 
 				"  },\r\n" + 
 				"  \"accuracy\": 50,\r\n" + 
 				"  \"name\": \"Google Shoes!\",\r\n" + 
 				"  \"phone_number\": \"(02) 9374 4000\",\r\n" + 
 				"  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\r\n" + 
 				"  \"types\": [\"shoe_store\"],\r\n" + 
 				"  \"website\": \"http://www.google.com.au/\",\r\n" + 
 				"  \"language\": \"en-AU\"\r\n" + 
 				"}";
		return a;
	}

}
