package ch.juventus.example.web;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class EmployeeStepDefs {

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private String employeeId;

    private HttpResponse httpResponse;

    @Given("^an employee with the id (\\d+)$")
    public void employeeIdGiven(String id) {
        employeeId = id;
    }

    @When("^the employee is retrieved from the service$")
    public void getRequest() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/employees/" + this.employeeId);
        request.addHeader("accept", "application/json");
        httpResponse = httpClient.execute(request);
    }

    @Then("^an employee should be returned$")
    public void success() {
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

}