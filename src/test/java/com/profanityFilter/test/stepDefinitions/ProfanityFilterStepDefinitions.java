package com.profanityFilter.test.stepDefinitions;

import com.profanityFilter.api.ProfanityFilterAPI;
import com.profanityFilter.api.ProfanityResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.xml.XmlPath;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ProfanityFilterStepDefinitions {

    @Steps
    ProfanityFilterAPI profanityFilterAPI;

    @When("Siva requests a {string} after filtering for profanity in specific {string}")
    public void sivaRequestsATextTextAfterFilteringForProfanityInSpecificFormatFormat(String text, String format) {
        profanityFilterAPI.filterProfanityForTextWithSpecificResultFormat(text,format);
    }

    @Then("he should be able to view the {string} returned in requested {string}")
    public void he_should_be_able_to_view_the_filtered_text(String filteredText, String format) {
        restAssuredThat(response -> response.statusCode(200));
        profanityFilterAPI.validateResponse(ProfanityResponse.result,filteredText,format);

    }

    @Then("he should be shown an {string} message in requested {string}")
    public void heShouldBeShownAnMessageInRequested(String errorMessage, String format) {
        restAssuredThat(response -> response.statusCode(200));
        profanityFilterAPI.validateResponse(ProfanityResponse.error,errorMessage,format);

    }

    @When("Siva requests a {string} to be checked for profanity")
    public void sivaRequestsAToBeCheckedForProfanity(String text) {
        profanityFilterAPI.checkProfanityInGivenText(text);
    }

    @Then("he should be shown with the {string} in plain text format")
    public void heShouldBeShownWithTheInPlainTextFormat(String result) {
        profanityFilterAPI.validateResponse(result);
    }

    @When("Siva requests a {string} after filtering using new {string} by replacing with {string} in {string}")
    public void sivaRequestsAAfterFilteringUsingNewByReplacingWithReplacementStringInFormat(String text, String newText, String replacementText, String format) {
        profanityFilterAPI.filterProfanityTextWithReplacementString(text,newText,replacementText,format);
    }

    @When("Siva requests a {string} after filtering using new {string} by replacing with {string} character in {string}")
    public void sivaRequestsAAfterFilteringUsingNewByReplacingWithCharacterIn(String text, String newText, String replacementChar, String format) {
        profanityFilterAPI.filterProfanityTextWithReplacementCharacter(text,newText,replacementChar,format);

    }
}
