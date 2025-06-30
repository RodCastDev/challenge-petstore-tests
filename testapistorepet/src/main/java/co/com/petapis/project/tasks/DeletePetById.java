package co.com.petapis.project.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static co.com.petapis.project.utils.Constantes.PET;

public class DeletePetById implements Task {

    private final Long petId;

    public DeletePetById(Long petId) {
        this.petId = petId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String endpointWithId = PET + "/" + petId;

        actor.attemptsTo(
                Delete.from(endpointWithId).with(
                        request -> request.relaxedHTTPSValidation().log().all()
                )
        );
    }

    public static DeletePetById withId(Long petId) {
        return Tasks.instrumented(DeletePetById.class, petId);
    }
}
