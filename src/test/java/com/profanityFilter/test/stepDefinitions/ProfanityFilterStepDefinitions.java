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
}
