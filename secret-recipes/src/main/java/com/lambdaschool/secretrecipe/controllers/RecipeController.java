package com.lambdaschool.secretrecipe.controllers;

import com.lambdaschool.secretrecipe.models.Recipe;
import com.lambdaschool.secretrecipe.models.User;
import com.lambdaschool.secretrecipe.repository.UserRepository;
import com.lambdaschool.secretrecipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @Autowired
    UserRepository userRepository;

//    `GET recipes/all`
//    Returns all recipes that the user is authorized to see. For admins, this is all recipes. For users, this is recipes where they are the owner or guest.
    @GetMapping(value = "/all",
            produces = "application/json")
    public ResponseEntity<?> listAuthedRecipes()
    {
        List<Recipe> result;
        // Todo: find all if logged in as Admin

        User current = userRepository.findByUsername(SecurityContextHolder.getContext()
                        .getAuthentication().getName());

        result = recipeService.findByOwnerOrGuest(current);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    `POST recipes/new`
//    Creates a new recipe.
    @PostMapping(value = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> postNewRecipe(@RequestBody Recipe recipe)
    {
        Recipe result = recipeService.save(recipe);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

//    `PUT recipes/{id}`
//    Updates the recipe with id {id}.
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> putRecipe(@RequestBody Recipe recipe, @PathVariable long id)
    {
        recipe.setRecipeid(id);
        Recipe result = recipeService.save(recipe);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    `PATCH recipes/{id}`
//    Updates the recipe with id {id}.
    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> patchRecipe(@RequestBody Recipe recipe, @PathVariable long id)
    {
        Recipe result = recipeService.update(recipe, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    `DELETE recipes/{id}`
//    Deletes the recipe with id {id}.
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable long id)
    {
        recipeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
