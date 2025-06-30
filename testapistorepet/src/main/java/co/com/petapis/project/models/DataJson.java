package co.com.petapis.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class DataJson {

    protected String consume;
    protected String typeCase;
    protected String name;
    protected String categoryName;
    protected String photoUrls;
    protected String status;


    public static DataJson configModel(Map<String, String> row ){
        return new DataJson(
                row.get("consume"),
                row.get("typeCase"),
                row.get("name"),
                row.get("categoryName"),
                row.get("photoUrls"),
                row.get("status")
        );
    }

}
