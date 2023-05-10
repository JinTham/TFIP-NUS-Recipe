package tfip.csf.recipe.Services;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfip.csf.recipe.Models.Recipe;
import tfip.csf.recipe.Repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepo;
    
    public Optional<List<Document>> getAllRecipes() {
        return recipeRepo.getAllRecipes();
    }

    public Optional<Document> getRecipe(String recipeId) {
        return recipeRepo.getRecipe(recipeId);
    }

    public String saveRecipe(Recipe recipe) {
        return recipeRepo.saveRecipe(recipe);
    }

}
