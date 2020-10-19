package com.lambdaschool.secretrecipe.services;

import com.lambdaschool.secretrecipe.models.Recipe;
import com.lambdaschool.secretrecipe.models.User;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();
    Recipe findById(Long id);
    Recipe findByName(String name);
    List<Recipe> findByOwner(User user);
    List<Recipe> findByOwnerOrGuest(User user);

    Recipe save(Recipe recipe);

    Recipe saveDirect(Recipe recipe);

    Recipe update(Recipe recipe, Long id);

    void deleteById(Long id);
    void deleteAll();
}
