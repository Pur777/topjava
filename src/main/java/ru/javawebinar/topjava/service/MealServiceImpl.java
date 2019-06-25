package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }


    @Override
    public Collection<Meal> getAll(int userID) {
        return repository.getAll(userID);
    }

    @Override
    public Meal get(int userID, int id) {
        Meal meal = repository.get(userID, id);
        if (meal == null) {
            throw new NotFoundException("get");
        }
        return meal;
    }

    @Override
    public void delete(int userID, int id) {
        if(!repository.delete(userID, id)) {
            throw new NotFoundException("delete");
        }
    }

    @Override
    public void save(int userID, Meal meal) {
        Meal saveMeal = repository.save(userID, meal);
        if (saveMeal == null) {
            throw new NotFoundException("save");
        }
    }

    @Override
    public void update(int userID, Meal meal) {
        Meal updateMeal = repository.update(userID, meal);
        if (updateMeal == null) {
            throw  new NotFoundException("update");
        }
    }
}