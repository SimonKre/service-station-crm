package pl.coderslab.carworkshop.controller;

import pl.coderslab.carworkshop.dao.CustomerDao;
import pl.coderslab.carworkshop.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "CustomerManager", urlPatterns = {"/customer/manager"})
public class CustomerManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birthdate = request.getParameter("birthdate");

        if(name != null && surname != null) {
            Customer customer = new Customer(name, surname, birthdate.length() > 0 ? LocalDate.parse(birthdate) : null);
            CustomerDao.saveToDb(customer);
        }

        response.sendRedirect(request.getServletContext().getContextPath() + "/customer/manager");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Customer> customers = CustomerDao.loadAll();

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/WEB-INF/views/customers-manager.jsp").forward(request, response);
    }
}
