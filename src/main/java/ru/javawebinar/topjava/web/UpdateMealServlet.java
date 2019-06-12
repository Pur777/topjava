package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class UpdateMealServlet extends HttpServlet {
    private MealDAO mealDAO = new MealDAOImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("id", id);

        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

        String id = req.getParameter("id");
        String date = req.getParameter("datetime");
        String name = req.getParameter("name");
        String calories = req.getParameter("calories");

        LocalDateTime localDateTime = LocalDateTime.parse(date);

        mealDAO.editMeal(Integer.valueOf(id), localDateTime, name, Integer.valueOf(calories));

        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}
