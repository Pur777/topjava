package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.Util;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_2_ID, USER_ID);
        assertMatch(meal, MEAL_2);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MEAL_4_ID, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL_4_ID, USER_ID);
        USER_MEALS.removeIf(meal -> meal.getId().equals(MEAL_4_ID));
        assertMatch(service.getAll(USER_ID), USER_MEALS);
        USER_MEALS.add(MEAL_4);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(MEAL_4_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> betweenDatesMeals = service
                .getBetweenDates(LocalDate.of(2015, 5, 30), LocalDate.of(2015, 5, 30), USER_ID);
        List<Meal> test = USER_MEALS.stream().filter(meal -> Util
                .isBetween(meal.getDate(), LocalDate.of(2015, 5, 30), LocalDate.of(2015, 5, 30)))
                .collect(Collectors.toList());
        assertMatch(betweenDatesMeals, test);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> betweenTimesMeals = service
                .getBetweenDateTimes(LocalDateTime.of(2015, 5, 30, 8, 0), LocalDateTime.of(2015, 5, 31, 14, 0), USER_ID);
        List<Meal> test = USER_MEALS.stream().filter(meal -> Util
                .isBetween(meal.getDateTime(), LocalDateTime.of(2015, 5, 30, 8, 0), LocalDateTime.of(2015, 5, 31, 14, 0)))
                .collect(Collectors.toList());
        assertMatch(betweenTimesMeals, test);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, USER_MEALS);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL_3);
        updated.setDescription("Test update");
        updated.setCalories(100);
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL_3_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception{
        Meal updated = new Meal(MEAL_3);
        updated.setDescription("Test update");
        updated.setCalories(100);
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.of(2019, 6, 24, 8, 0), "Тестовый завтрак", 1800);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        USER_MEALS.add(newMeal);
        assertMatch(service.getAll(USER_ID), USER_MEALS);
        USER_MEALS.removeIf(meal -> meal.getId().equals(newMeal.getId()));
    }
}