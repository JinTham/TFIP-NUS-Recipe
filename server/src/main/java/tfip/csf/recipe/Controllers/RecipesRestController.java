package tfip.csf.recipe.Controllers;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import tfip.csf.recipe.Models.Recipe;
import tfip.csf.recipe.Services.RecipeService;

@RestController
@RequestMapping
public class RecipesRestController {

    @Autowired
    private RecipeService recipeSvc;
    
    @GetMapping(path="/api/recipes", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRecipes() {
        Optional<List<Document>> opt = recipeSvc.getAllRecipes();
        List<Document> recipes = opt.get();
        JsonArrayBuilder jarrBuilder = Json.createArrayBuilder();
        for (Document recipe : recipes) {
            jarrBuilder.add(Recipe.doctoJSON(recipe));
        }
        JsonArray results = jarrBuilder.build();

        return ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(results.toString());
    }

    @GetMapping(path="/api/recipe/{recipeId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRecipe(@PathVariable String recipeId) {
        Optional<Document> opt = recipeSvc.getRecipe(recipeId);
        if (opt.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body("Recipe ID not found");
        }
        Document doc = opt.get();
        return ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Recipe.doctoJSON(doc).toString());
    }

    @PostMapping(path="/api/recipe", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postRecipe(@RequestBody Recipe recipe) {
        String resp = this.recipeSvc.saveRecipe(recipe);
        return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resp);
    }

}
