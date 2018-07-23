package pl.coderslab.carworkshop.controller;

import pl.coderslab.carworkshop.dao.OrderDao;
import pl.coderslab.carworkshop.dao.StatusDao;
import pl.coderslab.carworkshop.model.Order;
import pl.coderslab.carworkshop.model.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OrdersActual", urlPatterns = {"/"})
public class OrdersActual extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Order> orders = OrderDao.loadAll();
        ArrayList<Status> statuses = StatusDao.loadAll();

        request.setAttribute("orders", orders);
        request.setAttribute("statuses", statuses);
        request.getRequestDispatcher("/WEB-INF/views/actual-orders.jsp").forward(request, response);
    }
}
