package pl.coderslab.carworkshop.controller;

import pl.coderslab.carworkshop.dao.VehicleDao;
import pl.coderslab.carworkshop.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VehiclesByCustomer", urlPatterns = {"/vehicles/by-customer"})
public class VehiclesByCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        List<Vehicle> vehiclesByCustomer = null;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
            vehiclesByCustomer = VehicleDao.loadAllByCustomerId(id);
        }

        request.setAttribute("vehicles", vehiclesByCustomer);
        request.getRequestDispatcher("/WEB-INF/views/vehicles.jsp").forward(request, response);
    }
}
