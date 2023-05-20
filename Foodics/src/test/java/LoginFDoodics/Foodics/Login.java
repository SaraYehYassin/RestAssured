package LoginFDoodics.Foodics;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.Response;



public class Login {
    @Test(priority = 1)
   
    public void login_request()
    {
    	String basUurl= "https://pay2.foodics.dev/cp_internal";
    	String userName="merchant@foodics.com";
    	String password="123456";
    	RestAssured.baseURI =basUurl; 
    	
        Response response = RestAssured.given().contentType(ContentType.URLENC).
                body("AUTH_TOKEN=&j_username=" + userName + "&j_password=" + password + "&userName=&AUTH_TOKEN=").
                redirects().follow(false).
            expect().
                statusCode(302).
            when().
                post("/login");
        
        String headerLocationValue = response.getHeader("Location");

        Response resp2 =
        		RestAssured.given().expect().
                    statusCode(200).
                when().
                    get(headerLocationValue);

         assertEquals(resp2.statusCode(), 200);

        
      
    }
}
