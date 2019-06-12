package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAOImpl implements MealDAO{
    private static AtomicInteger id = new AtomicInteger(1);
    private static List<Meal> meals = new CopyOnWriteArrayList<>();

    static {
        meals.add(new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public List<Meal> allMeals() {
        return meals;
    }

    @Override
    public void addMeal(LocalDateTime localDateTime, String name, int calories) {
        meals.add(new Meal(id.getAndIncrement(), localDateTime, name, calories));
    }

    @Override
    public void delete(int id) {
        meals.removeIf(meal -> meal.getId() == id);
    }

    @Override
    public void editMeal(int id, LocalDateTime localDateTime, String name, int calories) {
        meals.removeIf(meal -> meal.getId() == id);
        meals.add(new Meal(id, localDateTime, name, calories));
    }
}
