package pl.coderslab.carworkshop.controller;

import pl.coderslab.carworkshop.dao.*;
import pl.coderslab.carworkshop.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "OrdersManager", urlPatterns = {"/orders/manager"})
public class OrdersManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String plannedStartStr = request.getParameter("plannedStart");
        String startStr = request.getParameter("start");
        String employeeIdStr = request.getParameter("employeeId");
        String problemDesc = request.getParameter("problemDesc");
        String repairDesc = request.getParameter("repairDesc");
        String statusIdStr = request.getParameter("statusId");
        String vehicleIdStr = request.getParameter("vehicleId");
        String totalCostStr = request.getParameter("totalCost");
        String partsCostStr = request.getParameter("partsCost");
        String hoursSpentStr = request.getParameter("hoursSpent");

        Integer employeeId = null;
        if (employeeIdStr != null && employeeIdStr.matches("[0-9]+")) {
            employeeId = Integer.parseInt(employeeIdStr);
        }

        Integer statusId = 1;
        if (statusIdStr != null && statusIdStr.matches("[0-9]+")) {
            statusId = Integer.parseInt(statusIdStr);
        }

        Double totalCost = null;
        if (totalCostStr != null && totalCostStr.matches("[+-]?([0-9]*[.])?[0-9]+")) {
            totalCost = Double.parseDouble(totalCostStr);
        }

        Double partsCost = null;
        if (partsCostStr != null && partsCostStr.matches("[+-]?([0-9]*[.])?[0-9]+")) {
            partsCost = Double.parseDouble(partsCostStr);
        }

        Integer vehicleId = null;
        if (vehicleIdStr != null && vehicleIdStr.matches("[0-9]+")) {
            vehicleId = Integer.parseInt(vehicleIdStr);
        }

        Float hoursSpent = null;
        if (hoursSpentStr != null && hoursSpentStr.matches("[+-]?([0-9]*[.])?[0-9]+")) {
            hoursSpent = Float.parseFloat(hoursSpentStr);
        }

        LocalDate plannedStart = plannedStartStr.length() > 0 ? LocalDate.parse(plannedStartStr) : null;
        LocalDate start = startStr.length() > 0 ? LocalDate.parse(startStr) : null;

        if (vehicleId != null) {
            Order order = new Order(plannedStart, start, employeeId, problemDesc, repairDesc, statusId, vehicleId, totalCost, partsCost, hoursSpent);
            OrderDao.saveToDb(order);
        }

        response.sendRedirect(request.getServletContext().getContextPath() + "/orders/manager");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Order> orders = OrderDao.loadAll();
        request.setAttribute("orders", orders);

        ArrayList<Status> statuses = StatusDao.loadAll();
        request.setAttribute("statuses", statuses);

        ArrayList<Vehicle> vehicles = VehicleDao.loadAll();
        request.setAttribute("vehicles", vehicles);

        ArrayList<Customer> customers = CustomerDao.loadAll();
        request.setAttribute("customers", customers);

        ArrayList<Employee> employees = EmployeeDao.loadAll();
        request.setAttribute("employees", employees);

        request.getRequestDispatcher("/WEB-INF/views/orders-manager.jsp").forward(request, response);
    }
}
