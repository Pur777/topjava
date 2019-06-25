package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private static int userID = SecurityUtil.authUserId();

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("getAllMeal");
        return MealsUtil.getWithExcess(service.getAll(userID), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getFilteredByDateTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getFilteredByDateTime");
        if (startDate == null) {
            startDate = LocalDate.MIN;
        }
        if (endDate == null) {
            endDate = LocalDate.MAX;
        }
        if (startTime == null) {
            startTime = LocalTime.MIN;
        }
        if (endTime == null) {
            endTime = LocalTime.MAX;
        }
        return MealsUtil.getFilteredWithExcess(service.getAll(userID), MealsUtil.DEFAULT_CALORIES_PER_DAY,
                LocalDateTime.of(startDate, startTime), LocalDateTime.of(endDate, endTime));
    }

    public Meal get(int id){
        log.info("get");
        return service.get(userID, id);
    }

    public void delete(int id) {
        log.info("delete");
        service.delete(userID, id);
    }

    public void save(Meal meal) {
        log.info("save");
        service.save(userID, meal);
    }

    public void update(Meal meal) {
        log.info("update");
        service.update(userID, meal);
    }
}