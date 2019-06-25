package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;

    public static final int MEAL_1_ID = START_SEQ + 2;
    public static final int MEAL_2_ID = START_SEQ + 3;
    public static final int MEAL_3_ID = START_SEQ + 4;
    public static final int MEAL_4_ID = START_SEQ + 5;
    public static final int MEAL_5_ID = START_SEQ + 6;
    public static final int MEAL_6_ID = START_SEQ + 7;
    public static final int MEAL_7_ID = START_SEQ + 8;
    public static final int MEAL_8_ID = START_SEQ + 9;

    public static final Meal MEAL_1 = new Meal(MEAL_1_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_2 = new Meal(MEAL_2_ID, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL_3 = new Meal(MEAL_3_ID, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_4 = new Meal(MEAL_4_ID, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL_5 = new Meal(MEAL_5_ID, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL_6 = new Meal(MEAL_6_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Meal MEAL_7 = new Meal(MEAL_7_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal MEAL_8 = new Meal(MEAL_8_ID, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> USER_MEALS = new CopyOnWriteArrayList<>();
    public static final List<Meal> ADMIN_MEALS = new CopyOnWriteArrayList<>();

    static {
        USER_MEALS.add(MEAL_1);
        USER_MEALS.add(MEAL_2);
        USER_MEALS.add(MEAL_3);
        USER_MEALS.add(MEAL_4);
        USER_MEALS.add(MEAL_5);
        USER_MEALS.add(MEAL_6);

        ADMIN_MEALS.add(MEAL_7);
        ADMIN_MEALS.add(MEAL_8);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user_id");
    }

    public static void assertMatch(List<Meal> actual, List<Meal> expected) {
        expected.sort((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
        assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
    }
}
