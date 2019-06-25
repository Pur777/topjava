package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final int USER_ID = 1;

    {
        MealsUtil.MEALS.forEach(meal -> save(USER_ID, meal));
    }

    @Override
    public Meal save(int userID, Meal meal) {
        Map<Integer, Meal> meals = repository.get(userID);
        if (meals == null) {
            meals = new HashMap<>();
        }
        int mealID = counter.incrementAndGet();
        meals.put(mealID, new Meal(mealID, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        repository.put(userID, meals);
        // treat case: update, but absent in storage
        return meal;
    }

    @Override
    public boolean delete(int userID, int id) {
        return repository.get(userID).remove(id) != null;
    }

    @Override
    public Meal get(int userID, int id) {
        return repository.get(userID).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userID) {
        return repository.get(userID).values().stream().sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())).collect(Collectors.toList());
    }

    @Override
    public Meal update(int userID, Meal meal) {
        if (repository.get(userID) == null || repository.get(userID).get(meal.getId()) == null) {
            return null;
        }
        repository.get(userID).put(meal.getId(), meal);
        return meal;
    }
}

