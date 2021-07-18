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
                String responseAsString = response.body().asString();
                XmlPath xmlPath = new XmlPath(responseAsString);
                Assert.assertEquals(xmlPath.get(path).toString(),text);
                break;
            case "plain":
                responseAsString = response.body().asString();
                Assert.assertEquals(responseAsString,text);
                break;
        }
    }
}
