package com.lambdaschool.secretrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "recipeingredients")
@IdClass(RecipeIngredientId.class)
public class RecipeIngredient extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "ingredients", allowSetters = true)
    private Recipe recipe;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "ingredientid")
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private Ingredient ingredient;

    public RecipeIngredient() {
    }

    public RecipeIngredient(@NotNull Recipe recipe, @NotNull Ingredient ingredient) {
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Objects.equals(getRecipe().getRecipeid(), that.getRecipe().getRecipeid()) &&
                Objects.equals(getIngredient().getIngredientid(), that.getIngredient().getIngredientid());
    }

    @Override
    public int hashCode() {
        return 124;
    }
}
