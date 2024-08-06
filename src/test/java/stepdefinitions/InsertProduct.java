package stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertEquals;

public class InsertProduct {
    public int statusCode;
    public RequestSpecification httpsRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;
    public JSONObject requestParams;

    @Given("I hit the url of post products api endpoint")
    public void iHitTheUrlOfPostProductsApiEndpoint() {
        RestAssured.baseURI ="https://fakestoreapi.com/";
        httpsRequest = RestAssured.given();
        requestParams = new JSONObject();
        requestParams.put("title", "shoes");
        requestParams.put("price", 12.5);
        requestParams.put("description", "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        requestParams.put("image", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        requestParams.put("category", "men's clothing");
    }

    @And("I pass the request body of product title Shoes")
    public void i_pass_the_request_body_of_product_title_shoes() {
        httpsRequest.body(requestParams.toJSONString());
        response = httpsRequest.put("products");
        body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }


    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAsId(String id) {
        JsonPath jsonPath = response.jsonPath();
        String s = jsonPath.getJsonObject("id").toString();
        assertEquals("21", s);
    }
}
