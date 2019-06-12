package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealTo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-MM-d HH:mm");

    private final int id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    public MealTo(int id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public boolean isExcess() {
        return excess;
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime.format(formatter);
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Дата " + dateTime.format(formatter) + " " + description + " " +
                ", калории: " + calories;
    }
}