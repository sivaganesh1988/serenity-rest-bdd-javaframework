package com.profanityFilter.api;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;

import static org.hamcrest.Matchers.equalTo;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class ProfanityFilterAPI {

    private EnvironmentVariables environmentVariables;


    public Response response;
    public String responseAsString;

    @Step("Filter input text {0} in response format {1}")
    public void filterProfanityForTextWithSpecificResultFormat(String text, String format) {
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservice.endpoint");
        response = SerenityRest.given()
                .pathParam("format", format)
                .queryParam("text", text)
                .get(webserviceEndpoint);
    }

    @Step("Validate response {0} {1} in format {2}")
    public void validateResponse(String path, String text, String format){
        switch(format){
            case "json":
                restAssuredThat(response -> response.body(path, equalTo(text)));
                break;
            case "xml":
                responseAsString = response.body().asString();
                XmlPath xmlPath = new XmlPath(responseAsString);
                Assert.assertEquals(xmlPath.get(path).toString(),text);
                break;
            case "plain":
                responseAsString = response.body().asString();
                Assert.assertEquals(responseAsString,text);
                break;
        }
    }
@Step
    public void checkProfanityInGivenText(String text) {
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservice.profanityCheck.endpoint");
        response = SerenityRest.given()
                .queryParam("text", text)
                .get(webserviceEndpoint);
    }

    @Step("Validate containsProfanity response {0} ")
    public void validateResponse(String result){
        responseAsString = response.body().asString();
        Assert.assertEquals(responseAsString,result);
    }

    @Step("Filter input text {0} by adding new text {1} by replacing {2} in response format {3}")
    public void filterProfanityTextWithReplacementString(String text, String add, String fill_text, String format) {
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservice.endpoint");
        response = SerenityRest.given()
                .pathParam("format", format)
                .queryParam("text", text)
                .queryParam("add",add)
                .queryParam("fill_text",fill_text)
                .get(webserviceEndpoint);
    }

    @Step("Filter input text {0} by adding new text {1} by replacing {2} char in response format {3}")
    public void filterProfanityTextWithReplacementCharacter(String text, String add, String fill_char, String format) {
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservice.endpoint");
        response = SerenityRest.given()
                .pathParam("format", format)
                .queryParam("text", text)
                .queryParam("add",add)
                .queryParam("fill_char",fill_char)
                .get(webserviceEndpoint);
    }
}
