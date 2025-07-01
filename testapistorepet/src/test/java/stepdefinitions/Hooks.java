package stepdefinitions;

import co.com.petapis.project.utils.SharedPetData;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@resetIds")
    public void resetValidPetIdsBeforeScenario() {
        SharedPetData.resetValidPetIds();

    }
}
