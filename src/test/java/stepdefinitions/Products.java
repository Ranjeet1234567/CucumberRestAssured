package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertEquals;

public class Products {
    public int statusCode;
    public RequestSpecification httpsRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;
    public JSONObject requestParams;

    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url in the request")
    public void i_pass_the_url_in_the_request() {
        httpsRequest = RestAssured.given();
        response = httpsRequest.get("products");
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        responseCode = response.getStatusCode();
        assertEquals(200, responseCode);
    }

    @Then("I verify that the rate of the first product is {}")
    public void I_verify_that_the_rate_of_the_first_product_is(String rate) {
        body = response.getBody();
        //Convert response body into String
        String responseBody = body.asString();
        JsonPath jsonPath = response.jsonPath();
        String s = jsonPath.getJsonObject("rating[0].rate").toString();
        assertEquals(rate, s);
    }
}
