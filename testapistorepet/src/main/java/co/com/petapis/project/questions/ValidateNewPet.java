package co.com.petapis.project.questions;

import co.com.petapis.project.utils.SharedPetData;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ValidateNewPet implements Question<Boolean> {

    @SneakyThrows
    @Override
    public Boolean answeredBy(Actor actor) {

        int statusCode = SerenityRest.lastResponse().statusCode();
        boolean isValid = false;
        System.out.println("Response Body:\n" + SerenityRest.lastResponse().getBody().asPrettyString());

        String testCase = actor.recall("case").toString();

        if ("pass".equalsIgnoreCase(testCase)) {
            Map<String, Object> responseBody = SerenityRest.lastResponse()
                    .getBody().jsonPath().getMap("");

            String nameResponse = String.valueOf(responseBody.get("name"));
            String statusResponse = String.valueOf(responseBody.get("status"));
            List<String> photoUrlsResponse = (List<String>) responseBody.get("photoUrls");
            Map<String, Object> category = (Map<String, Object>) responseBody.get("category");
            String categoryName = String.valueOf(category.get("name"));

            String expectedName = actor.recall("name");
            String expectedStatus = actor.recall("status");
            String expectedPhotoUrl = actor.recall("photoUrls");
            String expectedCategoryName = actor.recall("categoryName");

            boolean nameMatch = nameResponse.equalsIgnoreCase(expectedName);
            boolean statusMatch = statusResponse.equalsIgnoreCase(expectedStatus);
            boolean photoMatch = photoUrlsResponse.contains(expectedPhotoUrl);
            boolean categoryMatch = categoryName.equalsIgnoreCase(expectedCategoryName);
            boolean httpStatusOk = (statusCode == 200);

            isValid = nameMatch && statusMatch && photoMatch && categoryMatch && httpStatusOk;

            if (isValid) {
                Long newPetId = Long.valueOf(responseBody.get("id").toString());
                SharedPetData.addValidPetId(newPetId);
            }

        } else if ("error".equalsIgnoreCase(testCase)) {
            isValid = (statusCode == 400);
        }

        return isValid;
    }

    public static ValidateNewPet validateNewPet() {
        return new ValidateNewPet();
    }
}
