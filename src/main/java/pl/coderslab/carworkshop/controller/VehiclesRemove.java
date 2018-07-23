package pl.coderslab.carworkshop.controller;

import pl.coderslab.carworkshop.dao.VehicleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VehiclesRemove", urlPatterns = {"/vehicles/remove"})
public class VehiclesRemove extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null && request.getParameter("id").matches("[0-9]+")) {
            id = Integer.parseInt(request.getParameter("id"));
            VehicleDao.deleteById(id);
        }

        response.sendRedirect(request.getServletContext().getContextPath() + "/vehicles/manager");
    }
}
