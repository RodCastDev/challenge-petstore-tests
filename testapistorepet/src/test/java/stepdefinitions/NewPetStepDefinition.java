package stepdefinitions;

import co.com.petapis.project.models.DataJson;
import co.com.petapis.project.questions.ValidateNewPet;
import co.com.petapis.project.tasks.CreateNewPet;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static co.com.petapis.project.utils.Constantes.URL_BASE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class NewPetStepDefinition {

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Rodrigo");
    }

    @DataTableType(replaceWithEmptyString = "[void]")
    public DataJson configModel(Map<String,String> row){
        return DataJson.configModel(row);
    }

    @Given("the API is available")
    public void theAPIIsAvailable() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(URL_BASE));
    }


    @When("I send a POST request with the data")
    public void iSendAPOSTRequestWithTheData(List<DataJson> data) {
        theActorInTheSpotlight().attemptsTo(CreateNewPet.createNewPet(data.get(0)));
    }

    @Then("the service response is validated according to the case")
    public void theServiceResponseIsValidatedAccordingToTheCase() {
        theActorInTheSpotlight().should(seeThat(ValidateNewPet.validateNewPet(), Matchers.is(true)));
    }
}
