package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealService {
    Collection<Meal> getAll(int userID);

    Meal get(int userID, int id);

    void delete(int userID, int id);

    void save(int userID, Meal meal);

    void update(int userID, Meal meal);
}