package ca.cloudace.backend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class HelloSteps {

    @Given("I have a working setup")
    public void i_have_a_working_setup() {
        System.out.println("Setup is OK!");
    }

    @When("I run the test")
    public void i_run_the_test() {
        System.out.println("Running test...");
    }

    @Then("I should see {string}")
    public void i_should_see(String message) {
        System.out.println(message);
    }
}

