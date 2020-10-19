package com.lambdaschool.secretrecipe.services;

import com.lambdaschool.secretrecipe.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipe.models.Ingredient;
import com.lambdaschool.secretrecipe.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "ingredientService")
public class IngredientServiceImpl implements IngredientService{
    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> result = new ArrayList<>();
        ingredientRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public Ingredient findByName(String name) {
        return ingredientRepository.findByName(name);
    }

    @Override
    public Ingredient findById(long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find ingredient with ID " + id + "."));
    }

    @Override
    public void deleteAll() {
        ingredientRepository.deleteAll();
    }
}
