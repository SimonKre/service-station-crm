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

@WebServlet(name = "CustomerEditor", urlPatterns = {"/customer/editor"})
public class CustomerEditor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birthdate = request.getParameter("birthdate");

        if(name != null && surname != null) {
            Customer customer = new Customer(id, name, surname, birthdate.length() > 0 ? LocalDate.parse(birthdate) : null);
            CustomerDao.saveToDb(customer);
        }

        response.sendRedirect(request.getServletContext().getContextPath() + "/customer/manager");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        Customer customer = CustomerDao.loadById(id);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/WEB-INF/views/customers-editor.jsp").forward(request, response);
    }
}
