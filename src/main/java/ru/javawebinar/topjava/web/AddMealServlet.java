package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class AddMealServlet extends HttpServlet {
    private MealDAO mealDAO = new MealDAOImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

        String date = req.getParameter("datetime");
        String name = req.getParameter("name");
        String calories = req.getParameter("calories");

        LocalDateTime localDateTime = LocalDateTime.parse(date);

        mealDAO.addMeal(localDateTime, name, Integer.valueOf(calories));

        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}
