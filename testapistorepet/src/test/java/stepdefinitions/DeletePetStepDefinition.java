package stepdefinitions;

import co.com.petapis.project.questions.ValidateDeletePet;
import co.com.petapis.project.tasks.DeletePetById;
import co.com.petapis.project.utils.SharedPetData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class DeletePetStepDefinition {

    @Given("I prepare the DELETE ID {string} with value {string}")
    public void iPrepareDeleteId(String idOrigin, String idValue) {
        Long currentPetId;

        if (idOrigin.equalsIgnoreCase("valid")) {
            if (SharedPetData.hasValidIds()) {
                // Pulls and removes the first available valid ID
                currentPetId = SharedPetData.getAndRemoveFirstValidId();
                Serenity.setSessionVariable("currentPetId").to(currentPetId);
                System.out.println("Using and removing valid ID" + currentPetId);
            } else {
                throw new RuntimeException("No valid IDs left to delete.");
            }
        } else {
            try {
                currentPetId = Long.parseLong(idValue);
                Serenity.setSessionVariable("currentPetId").to(currentPetId);
                System.out.println("Using manual ID for DELETE: " + currentPetId);
            } catch (NumberFormatException e) {
                throw new RuntimeException("The manual ID value is not a valid number: " + idValue);
            }
        }
    }

    @When("I consume the endpoint to delete the pet with the provided ID")
    public void iConsumeTheEndpointToDeleteThePetWithProvidedID() {
        Long currentPetId = Serenity.sessionVariableCalled("currentPetId");

        Serenity.setSessionVariable("deletedPetId").to(currentPetId);

        theActorInTheSpotlight().attemptsTo(
                DeletePetById.withId(currentPetId)
        );

        System.out.println("Deleted pet with ID: " + currentPetId);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(Integer expectedStatus) {
        theActorInTheSpotlight().should(
                seeThat(ValidateDeletePet.withStatus(expectedStatus))
        );
    }
}
