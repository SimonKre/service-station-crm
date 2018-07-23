package pl.coderslab.carworkshop.controller;

import pl.coderslab.carworkshop.dao.CustomerDao;
import pl.coderslab.carworkshop.dao.VehicleDao;
import pl.coderslab.carworkshop.model.Customer;
import pl.coderslab.carworkshop.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "VehiclesEditor", urlPatterns = {"/vehicles/editor"})
public class VehiclesEditor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String model = request.getParameter("model");
        String maker = request.getParameter("maker");
        String productionYear = request.getParameter("productionYear");
        String registrationNumber = request.getParameter("registrationNumber");
        String nextOverhaul = request.getParameter("nextOverhaul");
        String customerId = request.getParameter("customerId");

        if(model != null && maker != null &&
                productionYear.matches("[0-9]+") && registrationNumber != null &&
                nextOverhaul != null && customerId.matches("[0-9]+")) {
            Vehicle vehicle = new Vehicle(id, model, maker, Integer.parseInt(productionYear), registrationNumber, LocalDate.parse(nextOverhaul), Integer.parseInt(customerId));
            VehicleDao.saveToDb(vehicle);
        }

        response.sendRedirect(request.getServletContext().getContextPath() + "/vehicles/manager");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        ArrayList<Customer> customers = CustomerDao.loadAll();
        request.setAttribute("customers", customers);

        Vehicle vehicle = VehicleDao.loadById(id);
        request.setAttribute("vehicle", vehicle);
        request.getRequestDispatcher("/WEB-INF/views/vehicles-editor.jsp").forward(request, response);
    }
}
