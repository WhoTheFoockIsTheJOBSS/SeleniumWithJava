package StepDefinitions;

import io.cucumber.java.en.*;

public class stepDefinitions {
    @Given("today is Sunday")
    public void today_is_sunday() {
        System.out.println("Given Step Implemented");
    }
    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_friday_yet() {
        System.out.println("When Step Implemented");
    }
    @Then("I should be told {string}")
    public void i_should_be_told(String string) {
        System.out.println("Then Step Implemented " + string);
    }
}
