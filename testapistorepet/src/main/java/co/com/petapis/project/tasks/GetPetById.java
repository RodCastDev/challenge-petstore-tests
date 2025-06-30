package co.com.petapis.project.tasks;

import lombok.AllArgsConstructor;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static co.com.petapis.project.utils.Constantes.PET;

@AllArgsConstructor
public class GetPetById implements Task {

    private final Long petId;

    @Override
    public <T extends Actor> void performAs(T actor) {
        String endpointWithId = PET + "/" + petId;

        actor.attemptsTo(
                Get.resource(endpointWithId).with(
                        request -> request.relaxedHTTPSValidation().log().all()
                )
        );

        actor.remember("currentPetId", petId);
    }

    public static GetPetById withId(Long petId) {
        return Tasks.instrumented(GetPetById.class, petId);
    }
}
