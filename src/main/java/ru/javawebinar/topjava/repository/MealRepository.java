package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    Meal save(int userID, Meal meal);

    // false if not found
    boolean delete(int userID, int id);

    // null if not found
    Meal get(int userID, int id);

    Collection<Meal> getAll(int userID);

    Meal update(int userID, Meal meal);
}
