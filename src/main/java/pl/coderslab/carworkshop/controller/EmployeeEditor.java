package pl.coderslab.carworkshop.controller;

import pl.coderslab.carworkshop.dao.EmployeeDao;
import pl.coderslab.carworkshop.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeEditor", urlPatterns = {"/employee/editor"})
public class EmployeeEditor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String adress = request.getParameter("adress");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
        Double manHour = null;
        if (request.getParameter("man_hour") != null && request.getParameter("man_hour").matches("[+-]?([0-9]*[.])?[0-9]+")) {
            manHour = Double.parseDouble(request.getParameter("man_hour"));
        }

        if(name != null && surname != null && adress != null && manHour != null) {
            Employee employee = new Employee(id, name, surname, adress, phone, note, manHour);
            EmployeeDao.saveToDb(employee);
        }

        response.sendRedirect(request.getServletContext().getContextPath() + "/employee/manager");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        Employee employee = EmployeeDao.loadById(id);
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/WEB-INF/views/employee-editor.jsp").forward(request, response);
    }
}
