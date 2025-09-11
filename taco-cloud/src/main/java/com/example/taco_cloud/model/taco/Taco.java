package com.example.taco_cloud.model.taco;

import com.example.taco_cloud.model.Ingredient;
import lombok.Data;

import java.util.List;

@Data
public class Taco {

    private String name;
    private List<Ingredient> ingredients;
}
