package tfip.csf.recipe.Models;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Recipe implements Serializable {
    
    private Integer id;
    private String name;
    private List<String> ingredientsArr;
    private String instruction;
    
    public Recipe() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredientsArr() {
        return ingredientsArr;
    }

    public void setIngredientsArr(List<String> ingredients) {
        this.ingredientsArr = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public static JsonObject toJSON(Recipe recipe) {
        JsonArrayBuilder jarrBuilder = Json.createArrayBuilder();
        for (String i : recipe.getIngredientsArr()) {
            jarrBuilder.add(i);
        }
        JsonArray ingredients = jarrBuilder.build();
        return Json.createObjectBuilder()
                    .add("id",recipe.getId())
                    .add("name",recipe.getName())
                    .add("ingredientsArr",ingredients)
                    .add("instruction",recipe.getInstruction())
                    .build();
    }

    public static JsonObject doctoJSON(Document doc) { 
        JsonArrayBuilder jarrBuilder = Json.createArrayBuilder();
        Document recipe = (Document) doc.get("recipe");
        List<String> ingredientsArr = recipe.get("ingredientsArr",List.class);
        for (String i : ingredientsArr) {
            jarrBuilder.add(i);
        }
        JsonArray ingredients = jarrBuilder.build();
        return Json.createObjectBuilder()
                    .add("id",doc.getObjectId("_id").toString())
                    .add("name",recipe.getString("name"))
                    .add("ingredientsArr",ingredients)
                    .add("instruction",recipe.getString("instruction"))
                    .build();
    }

}
