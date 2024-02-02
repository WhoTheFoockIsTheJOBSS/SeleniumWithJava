package StepDefinitions;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.*;


import java.util.List;
import java.util.Map;

public class dataSetsSteps {
    @When("User types {string} and {string}")
    public void user_types_and(String username, String password) {
        System.out.println("Username is: " + username);
        System.out.println("Password is: " + password);

    }
    @And("My credentials are {string}")
    public void my_credentials_are(String credentials) {
        System.out.println("Your credentials are: " + credentials);

    }
    @Then("The {string} message should be displayed")
    public void the_message_should_be_displayed(String message) {
        System.out.println(message);

    }

    @Given("I have the following text:")
    public void i_have_the_following_text(String docString) {
        System.out.println(docString);
    }

    @DataTableType
    public loginData convert(Map<String, String> entry){
        return new loginData(
                entry.get("username"),
                entry.get("password")
        );
    }

    @Given("I login with the following info")
    public void i_login_with_the_following_info(List<loginData> loginDataList) {
        System.out.println("Username: " + loginDataList.get(0).username);
        System.out.println("Password: " + loginDataList.get(1).password);
    }

    private static class loginData {
        public String username;
        public String password;

        public loginData(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
