package LoginFDoodics.Foodics;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class FoodicsTCs {
	
	String basUurl= "https://pay2.foodics.dev/cp_internal";
	String userName="merchant@foodics.com";
	String password="123456";
	
	   @Test(priority = 1)
	   
	    public void User_login_Successfully()
	    {
		    
	    	RestAssured.baseURI =basUurl; 	    	
	        Response response = RestAssured.given().contentType(ContentType.URLENC).
	                body("AUTH_TOKEN=&j_username=" + userName + "&j_password=" + password + "&userName=&AUTH_TOKEN=").
	                redirects().follow(false).expect().statusCode(302).when().post("/login");
	        
	        String headerLocationValue = response.getHeader("Location");

	        Response resp2 =RestAssured.given().expect().statusCode(200).
	                when().get(headerLocationValue);

	         assertEquals(resp2.statusCode(), 200);
	         System.out.println("User successfully logined");
	         
	       //  ResponseBody body = resp2.getBody();
	        // String bodyAsString = body.asString();
	        // System.out.println(bodyAsString);
	      
	    }
	   
	   
	   @Test(priority = 2)
	   
	    public void VerifyHomePageContent()
	    {
		   
		   Response response = RestAssured.given()
           .contentType(ContentType.JSON).log().all()
           .when()
           .get("/login")
           .then()
           .extract().response();
		   
		   ResponseBody body = response.getBody();

		     String bodyAsString = body.asString();

		   assertEquals(bodyAsString.contains("Foodics Pay is a payment solution integrated with your Foodics Cashier App. Accept all card payments securely, with daily bank account sync") /*Expected value*/, true /*Actual Value*/);
		   System.out.println("Response body contains the right content");
	     
	    }
	   
	   
	   @Test(priority = 3)
	   
	    public void VerifyThat_ICanChageFontFrom_whoami()
	    {
		  // String basUurl= "https://pay2.foodics.dev/cp_internal";
			
	    	
	    	RestAssured.baseURI =basUurl;
	    		    	
	    	 Response response = RestAssured.given()
	                 .contentType(ContentType.JSON).log().all()
	                 .when()
	                 .get("/whoami")
	                 .then()
	                 .extract().response();

	    assertEquals(200, response.statusCode());
	    
	  ResponseBody body = response.getBody();

     String bodyAsString = body.asString();
     assertEquals(bodyAsString.contains("https://fonts.googleapis.com") /*Expected value*/, true /*Actual Value*/);
     System.out.println("Response body contains the right content");
  
	       
	     }

	        
	      
	    }

