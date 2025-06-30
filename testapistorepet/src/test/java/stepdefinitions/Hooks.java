package stepdefinitions;

import co.com.petapis.project.utils.SharedPetData;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@resetIds")
    public void resetValidPetIdsBeforeScenario() {
        SharedPetData.resetValidPetIds();

        System.out.println("🔄 Lista de IDs válidos reiniciada.");
        System.out.println("📋 IDs actuales en la lista: " + SharedPetData.getCurrentIds());
    }
}
