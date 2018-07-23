package pl.coderslab.carworkshop.controller;

import pl.coderslab.carworkshop.dao.CustomerDao;
import pl.coderslab.carworkshop.dao.OrderDao;
import pl.coderslab.carworkshop.dao.StatusDao;
import pl.coderslab.carworkshop.dao.VehicleDao;
import pl.coderslab.carworkshop.model.Customer;
import pl.coderslab.carworkshop.model.Order;
import pl.coderslab.carworkshop.model.Status;
import pl.coderslab.carworkshop.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "VehiclesDetails", urlPatterns = {"/vehicles/details"})
public class VehiclesDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        ArrayList<Customer> customers = CustomerDao.loadAll();
        request.setAttribute("customers", customers);
        ArrayList<Order> orders = OrderDao.loadAllByVehicleId(id);
        request.setAttribute("orders", orders);
        ArrayList<Status> statuses = StatusDao.loadAll();
        request.setAttribute("statuses", statuses);

        Vehicle vehicle = VehicleDao.loadById(id);
        request.setAttribute("vehicle", vehicle);
        request.getRequestDispatcher("/WEB-INF/views/vehicles-details.jsp").forward(request, response);
    }
}
