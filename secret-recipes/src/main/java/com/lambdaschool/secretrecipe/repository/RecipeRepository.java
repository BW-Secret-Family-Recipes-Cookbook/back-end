package com.lambdaschool.secretrecipe.repository;

import com.lambdaschool.secretrecipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
