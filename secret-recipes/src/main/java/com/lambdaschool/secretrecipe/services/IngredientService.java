package com.lambdaschool.secretrecipe.services;

import com.lambdaschool.secretrecipe.models.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();

    Ingredient findByName(String name);

    Ingredient findById(long id);

    void deleteAll();
}
