package tfip.csf.recipe.Repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import tfip.csf.recipe.Models.Recipe;

@Repository
public class RecipeRepository {

    private static final String MONGODB_COLLECTION = "recipe";

    @Autowired
    private MongoTemplate mongoTemplate;
    
    // db.getCollection("recipe").find({})
    public Optional<List<Document>> getAllRecipes() {
        // List<Recipe> recipes = new LinkedList<>();
        // Recipe recipe = new Recipe();
        // recipe.setId(1);
        // recipe.setName("bread");
        // List<String> ingredients = new LinkedList<>();
        // ingredients.add("flour");
        // ingredients.add("water");
        // recipe.setIngredientsArr(ingredients);
        // recipe.setInstruction("Put everything together");
        // recipes.add(recipe);

        // Recipe recipe2 = new Recipe();
        // recipe2.setId(1);
        // recipe2.setName("roast beef");
        // List<String> ingredients2 = new LinkedList<>();
        // ingredients2.add("beef");
        // ingredients2.add("sauce");
        // recipe2.setIngredientsArr(ingredients2);
        // recipe2.setInstruction("Put everything together");
        // recipes.add(recipe2);

        List<Document> result = mongoTemplate.findAll(Document.class, MONGODB_COLLECTION);
        if (result == null || result.size()<1) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    public Optional<Document> getRecipe(String recipeId) {
        System.out.println(recipeId);
        ObjectId objectId = new ObjectId(recipeId);
        Query query = Query.query(Criteria.where("_id").is(objectId));
        List<Document> doc = mongoTemplate.find(query, Document.class, MONGODB_COLLECTION);
        System.out.println(doc);
        if (doc == null || doc.size()<1) {
            return Optional.empty();
        }
        return Optional.of(doc.get(0));
    }

    public String saveRecipe(Recipe recipe) {
        Document toSave = new Document();
        toSave.put("recipe",recipe);
        this.mongoTemplate.insert(toSave, MONGODB_COLLECTION);
        return "Recipe saved";
    }

}
