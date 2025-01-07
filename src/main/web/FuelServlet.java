package web;

import dao.FuelDAO;
import model.Fuel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/fuel")
public class FuelServlet extends HttpServlet {
    private FuelDAO fuelDAO = new FuelDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listFuels(request, response);
                break;
            case "details":
                showFuelDetails(request, response);
                break;
            default:
                listFuels(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addFuel(request, response);
        }
    }

    private void listFuels(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fuel> fuels = fuelDAO.getAllFuels();
        request.setAttribute("fuels", fuels);
        request.getRequestDispatcher("/jsp/fuel/list.jsp").forward(request, response);
    }

    private void showFuelDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Fuel fuel = fuelDAO.getFuelById(id);
        if (fuel != null) {
            request.setAttribute("fuel", fuel);
            request.getRequestDispatcher("/jsp/fuel/details.jsp").forward(request, response);
        } else {
            response.getWriter().write("Fuel not found.");
        }
    }

    private void addFuel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fuelType = request.getParameter("fuelType");
        double pricePerLitre = Double.parseDouble(request.getParameter("pricePerLitre"));
        int quantityAvailable = Integer.parseInt(request.getParameter("quantityAvailable"));

        Fuel fuel = new Fuel(0, fuelType, pricePerLitre, quantityAvailable);
        boolean success = fuelDAO.addFuel(fuel);

        if (success) {
            response.sendRedirect("fuel?action=list");
        } else {
            response.getWriter().write("Error: Could not add fuel record.");
        }
    }
}
