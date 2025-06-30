package co.com.petapis.project.utils;

import co.com.petapis.project.models.DataJson;

import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
public class BodyRequest {

    public static String requestNewPed(DataJson dataJson){
        String pathJson = Routes.valueOf(dataJson.getConsume()).getRouterFile();
        String firma ="";
        try {
            firma = new String(Files.readAllBytes(Paths.get(pathJson)));
            firma = firma.replace("$name",dataJson.getName())
                    .replace("$categoryName",dataJson.getCategoryName())
                    .replace("$photoUrls",dataJson.getPhotoUrls())
                    .replace("$status",dataJson.getStatus());

        }catch (Exception e){
            log.error(e);
        }
        return firma;
    }

}
