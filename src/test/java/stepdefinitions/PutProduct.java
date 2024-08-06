package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class PutProduct {
    public int statusCode;
    public RequestSpecification httpsRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;
    public JSONObject requestParams;

    @Given("I hit the url of put products api endpoint")
    public void iHitTheUrlOfPutProductsApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams=new JSONObject();
    }

    @When("I pass the url in the request with {}")
    public void iPassTheUrlInTheRequestWithProductNumber(String productNumber) {
        httpsRequest = RestAssured.given();
        requestParams.put("title", "test product");
        requestParams.put("price", 12.5);
        requestParams.put("description", "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        requestParams.put("image", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        requestParams.put("category", "men's clothing");
        httpsRequest.body(requestParams.toJSONString());
        response = httpsRequest.put("product" + productNumber);
        body = response.getBody();
        JsonPath jsonPath = response.jsonPath();
        String s = jsonPath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

}
