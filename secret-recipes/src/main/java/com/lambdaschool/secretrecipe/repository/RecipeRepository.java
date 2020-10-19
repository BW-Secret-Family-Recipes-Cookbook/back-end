package com.lambdaschool.secretrecipe.repository;

import com.lambdaschool.secretrecipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findByName(String name);

    List<Recipe> findAllByName(String name);
}
