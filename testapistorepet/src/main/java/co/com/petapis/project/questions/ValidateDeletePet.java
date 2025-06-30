package co.com.petapis.project.questions;

import lombok.SneakyThrows;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidateDeletePet implements Question<Boolean> {

    private final int expectedStatus;

    public ValidateDeletePet(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    @SneakyThrows
    @Override
    public Boolean answeredBy(Actor actor) {
        int actualStatus = SerenityRest.lastResponse().statusCode();

        System.out.println("Validando GET para status esperado: " + expectedStatus + ", recibido: " + actualStatus);
        System.out.println("Response Body:\n" + SerenityRest.lastResponse().getBody().asPrettyString());

        return actualStatus == expectedStatus;
    }

    public static ValidateDeletePet withStatus(int expectedStatus) {
        return new ValidateDeletePet(expectedStatus);
    }
}
