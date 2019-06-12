package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDAO {
    List<Meal> allMeals();
    void addMeal(LocalDateTime localDateTime, String name, int calories);
    void delete(int id);
    void editMeal(int id, LocalDateTime localDateTime, String name, int calories);
}
