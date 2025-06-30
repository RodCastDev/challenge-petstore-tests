package stepdefinitions;

import co.com.petapis.project.questions.ValidateGetPet;
import co.com.petapis.project.tasks.GetPetById;
import co.com.petapis.project.utils.SharedPetData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class GetPetStepDefinition {

    @Given("I prepare the ID {string} with value {string}")
    public void iPrepareTheIdWithValue(String idOrigin, String idValue) {
        Long currentPetId;

        if (idOrigin.equalsIgnoreCase("valid")) {
            if (Serenity.hasASessionVariableCalled("currentPetId")) {
                currentPetId = Serenity.sessionVariableCalled("currentPetId");
                System.out.println("Reusing session ID: " + currentPetId);
            } else if (SharedPetData.hasValidIds()) {
                currentPetId = SharedPetData.getAndRemoveFirstValidId();
                Serenity.setSessionVariable("currentPetId").to(currentPetId);
                System.out.println("Using generated valid ID and removed from list " + currentPetId);
            } else {
                throw new RuntimeException("No valid IDs available to test.");
            }
        } else {
            try {
                currentPetId = Long.parseLong(idValue);
                Serenity.setSessionVariable("currentPetId").to(currentPetId);
                System.out.println("Using manual ID: " + currentPetId);
            } catch (NumberFormatException e) {
                throw new RuntimeException("The manual ID value is not a valid number: " + idValue);
            }
        }
    }

    @When("I consume the endpoint to retrieve a pet with the provided ID")
    public void iConsumeTheEndpointToRetrievePetWithProvidedID() {
        Long currentPetId = Serenity.sessionVariableCalled("currentPetId");

        theActorInTheSpotlight().attemptsTo(
                GetPetById.withId(currentPetId)
        );
    }

    @Then("the response status should be {int} and the {string}")
    public void theResponseStatusShouldBeAndTheName(Integer expectedStatus, String expectedName) {
        theActorInTheSpotlight().remember("expectedName", expectedName);

        theActorInTheSpotlight().should(
                seeThat(ValidateGetPet.withExpected(expectedStatus))
        );
    }
}
