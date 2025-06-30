package co.com.petapis.project.tasks;

import co.com.petapis.project.models.DataJson;
import co.com.petapis.project.utils.BodyRequest;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;


import static co.com.petapis.project.utils.Constantes.PET;

public class CreateNewPet implements Task {

    private final DataJson dataJson;

    public CreateNewPet(DataJson dataJson) {
        this.dataJson = dataJson;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(PET).with(
                        requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                .contentType(ContentType.JSON).body(BodyRequest.requestNewPed(dataJson)).log().all()
                )
        );
        actor.remember("case",this.dataJson.getTypeCase());
        actor.remember("name",this.dataJson.getName());
        actor.remember("categoryName",this.dataJson.getCategoryName());
        actor.remember("photoUrls",this.dataJson.getPhotoUrls());
        actor.remember("status",this.dataJson.getStatus());
    }
    public static CreateNewPet createNewPet(DataJson dataJson){
        return Tasks.instrumented(CreateNewPet.class, dataJson);
    }
}
