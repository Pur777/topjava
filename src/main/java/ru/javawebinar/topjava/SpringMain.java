package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));

            MealRestController controller = appCtx.getBean(MealRestController.class);

            controller.getAll().forEach(System.out::println);
            System.out.println();
            controller.getFilteredByDateTime(LocalDate.of(2015, 5, 30), LocalTime.of(8, 0),
                LocalDate.of(2019, 12, 31), LocalTime.of(21, 0)).forEach(System.out::println);

            System.out.println(controller.get(2));

            controller.save(new Meal(LocalDateTime.of(2019, 5, 8, 12, 0), "Тест добавить", 100));
            controller.getAll().forEach(System.out::println);

            controller.update(new Meal(5, LocalDateTime.of(2019, 5, 8, 12, 0), "Тест редактировать", 100));
            controller.getAll().forEach(System.out::println);

            controller.delete(2);
            controller.getAll().forEach(System.out::println);
        }
    }
}
