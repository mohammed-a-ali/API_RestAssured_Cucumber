package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import static globalVariables.globals.*;

public class GeneralSteps {
    @Given("I am on endpoint {string}")
    public void iAmOnEndpoint(String endPoint) {
        endPointURI = endPoint;
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(responseOptions.getStatusCode(), statusCode);
    }

    @And("the response should have {string} with {string}")
    public void theShouldBe(String key, String value) {
        if(value.toLowerCase().equals("true") || value.toLowerCase().equals("false"))
            Assert.assertEquals(responseOptions.getBody().jsonPath().getBoolean(key), value.toLowerCase().equals("true"));
        else
            Assert.assertEquals(responseOptions.getBody().jsonPath().getString(key), value);
    }
}
