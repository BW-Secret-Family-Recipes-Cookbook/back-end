package com.lambdaschool.secretrecipe.services;

import com.lambdaschool.secretrecipe.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipe.models.Ingredient;
import com.lambdaschool.secretrecipe.models.Recipe;
import com.lambdaschool.secretrecipe.models.RecipeIngredient;
import com.lambdaschool.secretrecipe.models.User;
import com.lambdaschool.secretrecipe.repository.IngredientRepository;
import com.lambdaschool.secretrecipe.repository.RecipeRepository;
import com.lambdaschool.secretrecipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public List<Recipe> findAll() {
        List<Recipe> result = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find recipe with id " + id + "."));
    }

    @Override
    public Recipe findByName(String name) {
        return recipeRepository.findByName(name);
    }

//    @Override
//    public List<Recipe> findByOwner(User user) {
//        return null;
//    }
//
//    @Override
//    public List<Recipe> findByOwnerOrGuest(User user) {
//        return null;
//    }

    @Override
    public Recipe save(Recipe recipe) {
        Recipe newRecipe = new Recipe();

        if(recipe.getRecipeid() != 0)
        {
            Recipe oldRecipe = recipeRepository.findById(recipe.getRecipeid()).orElseThrow(() -> new ResourceNotFoundException("Could not find recipe with id " + recipe.getRecipeid() + "."));
            newRecipe.setRecipeid(oldRecipe.getRecipeid());
        }

        newRecipe.setName(recipe.getName());
        newRecipe.setCategory(recipe.getCategory());
        newRecipe.setSource(recipe.getSource());
        newRecipe.setInstructions(recipe.getInstructions());

        for(RecipeIngredient ri : recipe.getIngredients())
        {
            Ingredient ing = ingredientRepository.findByName(ri.getRecipe().getName());
            if(ing == null)
            {
                ing = ingredientRepository.save(new Ingredient(ri.getIngredient().getName()));
            }
            newRecipe.getIngredients().add(new RecipeIngredient(newRecipe, ing));
        }

        return recipeRepository.save(newRecipe);
    }

    @Override
    public Recipe update(Recipe recipe, Long id) {
        Recipe newRecipe = new Recipe();

        Recipe oldRecipe = recipeRepository.findById(recipe.getRecipeid()).orElseThrow(() -> new ResourceNotFoundException("Could not find recipe with id " + recipe.getRecipeid() + "."));

        newRecipe.setRecipeid(id);

        if(recipe.getName() != null) newRecipe.setName(recipe.getName());
        if(recipe.getCategory() != null) newRecipe.setCategory(recipe.getCategory());
        if(recipe.getSource() != null) newRecipe.setSource(recipe.getSource());
        if(recipe.getInstructions() != null) newRecipe.setInstructions(recipe.getInstructions());

        for(RecipeIngredient ri : recipe.getIngredients())
        {
            Ingredient ing = ingredientRepository.findByName(ri.getIngredient().getName());
            if(ing == null)
            {
                ing = ingredientRepository.save(new Ingredient(ri.getIngredient().getName()));
            }
            newRecipe.getIngredients().add(new RecipeIngredient(newRecipe, ing));
        }

        return recipeRepository.save(newRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        recipeRepository.deleteAll();
    }
}
