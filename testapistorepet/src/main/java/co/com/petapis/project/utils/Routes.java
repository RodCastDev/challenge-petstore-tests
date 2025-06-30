package co.com.petapis.project.utils;

import lombok.Getter;

@Getter
public enum Routes {

    NEW_PET_BODY("src/test/resources/files/Body.json");

    protected String routerFile;

    Routes(String routerFile){
        this.routerFile= routerFile;
    }

}
