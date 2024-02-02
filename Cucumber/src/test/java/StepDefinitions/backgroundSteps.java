package StepDefinitions;

import io.cucumber.java.en.*;

public class backgroundSteps {
    @Given("I have navigated to the page")
    public void i_have_navigated_to_the_page() {
        System.out.println("Navigate to the page");
    }
    @When("I provide valid credentials after clicking on log in tab")
    public void i_provide_valid_credentials_after_clicking_on_log_in_tab() {
        System.out.println("Provide valid credentials");
    }
    @Then("i should be logged in")
    public void i_should_be_logged_in() {
        System.out.println("Log in successful");
    }

    @Then("An alert should be prompted")
    public void anAlertShouldBePrompted() {
        System.out.println("An alert is visible");
    }

    @When("I click on the {string} product")
    public void iClickOnTheProduct(String arg0) {
        System.out.println("Log in successful");
    }

    @Then("I should be redirected to the product page")
    public void iShouldBeRedirectedToTheProductPage() {
        System.out.println("I navigate to the product page");
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String arg0) {
        System.out.println("I click on "+arg0);
    }

    @Then("I have added a product")
    public void iHaveAddedAProduct() {
        System.out.println("I have added a product");
    }

    @Then("It should appear on the cart page")
    public void itShouldAppearOnTheCartPage() {
        System.out.println("product in the cart");
    }

    @And("I click on {string} button")
    public void iClickOnButton(String arg0) {
        System.out.println("click on "+arg0);
    }

    @And("I complete the place order form")
    public void iCompleteThePlaceOrderForm() {
        System.out.println("complete the place order form");
    }

    @Then("I should see a {string} alert")
    public void iShouldSeeAAlert(String arg0) {
        System.out.println("the "+arg0+" alert is visible");
    }
}
