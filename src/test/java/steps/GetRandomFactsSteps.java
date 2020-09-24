package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static globalVariables.globals.*;

public class GetRandomFactsSteps {
    @When("I send {string} request with these parameters")
    public void iSendRequestWithTheseParameters(String method, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        HashMap<String, Object> params = new HashMap<>();
        params.put("animal_type", data.get(0).get("animal_type").toLowerCase());

        String amount = data.get(0).get("amount");
        if (Integer.parseInt(data.get(0).get("amount")) <= 0)
            amount = "1";
        params.put("amount", amount);

        restAssuredExtension = new RestAssuredExtension(endPointURI, method, null);
        responseOptions = restAssuredExtension.ExecuteWithParams(params);

        if (amount.equals("1")) {
            firstFactId = responseOptions.body().jsonPath().get("_id");
            firstUserId = responseOptions.body().jsonPath().get("user");
        } else {
            firstFactId = (String) responseOptions.getBody().jsonPath().getList("_id").get(0);
            firstUserId = (String) responseOptions.getBody().jsonPath().getList("user").get(0);
        }
    }
}
