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
import java.util.List;

@WebServlet(name = "OrdersByCustomer", urlPatterns = {"/orders/by-customer"})
public class OrdersByCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        List<Order> ordersByCustomer = null;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
            ordersByCustomer = OrderDao.loadAllByCustomerId(id);
        }

        ArrayList<Status> statuses = StatusDao.loadAll();
        request.setAttribute("statuses", statuses);

        request.setAttribute("orders", ordersByCustomer);
        request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
    }
}
