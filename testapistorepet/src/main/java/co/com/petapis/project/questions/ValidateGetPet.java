package co.com.petapis.project.questions;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

@AllArgsConstructor
public class ValidateGetPet implements Question<Boolean> {

    private final int expectedStatus;

    @SneakyThrows
    @Override
    public Boolean answeredBy(Actor actor) {
        int actualStatus = SerenityRest.lastResponse().statusCode();

        System.out.println("Expected status: " + expectedStatus + ", received: " + actualStatus);
        System.out.println("Response Body:\n" + SerenityRest.lastResponse().getBody().asPrettyString());

        if (expectedStatus == 200) {
            String nameInResponse = SerenityRest.lastResponse().jsonPath().getString("name");
            String expectedName = actor.recall("expectedName");

            System.out.println("Expected name: '" + expectedName + "' with received: '" + nameInResponse + "'");
            return expectedName.equalsIgnoreCase(nameInResponse) && actualStatus == 200;
        } else {
            return actualStatus == expectedStatus;
        }
    }

    public static ValidateGetPet withExpected(int status) {
        return new ValidateGetPet(status);
    }
}
