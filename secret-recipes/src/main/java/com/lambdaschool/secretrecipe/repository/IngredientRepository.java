package com.lambdaschool.secretrecipe.repository;

import com.lambdaschool.secretrecipe.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}
