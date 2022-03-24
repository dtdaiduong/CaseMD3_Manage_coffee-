package controller;

import dao.DrinkDAO;
import model.Drink;
import utils.ValidateUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;


@WebServlet(name = "DrinkController", value = "/drinks")
public class DrinkController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DrinkDAO drinkDAO;

    public void init() {
        drinkDAO = new DrinkDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html/charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "showActive":
                showActiveDrink(request, response);
                break;
            case "showDeActive":
                showDeActiveDrink(request, response);
                break;
            case "active":
                trading(request, response);
                break;
            case "stop":
                stopTrading(request, response);
                break;
            case "drinks":
            default:
                listDrink(request, response);
                break;
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/resource/display/addDrink.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int drinkId = Integer.parseInt(request.getParameter("drinkId"));
        Drink drink = drinkDAO.selectDrink(drinkId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/resource/display/editDrink.jsp");
        request.setAttribute("drink", drink);
        dispatcher.forward(request, response);
    }

    private void showActiveDrink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Drink> drinkList = drinkDAO.selectDrinkInStock();
        request.setAttribute("drinkList", drinkList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/resource/display/listDrink.jsp");
        dispatcher.forward(request, response);
    }

    private void showDeActiveDrink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Drink> drinkList = drinkDAO.selectDrinkOutOfStock();
        request.setAttribute("drinkList", drinkList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("resource/display/listDrinkUnLock.jsp");
        dispatcher.forward(request, response);
    }

    private void listDrink(HttpServletRequest request, HttpServletResponse response) {
        List<Drink> drinkList = drinkDAO.selectAllDrink();
        request.setAttribute("drinkList", drinkList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("resource/display/listDrink.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void trading(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int drinkId = Integer.parseInt(request.getParameter("drinkId"));
        drinkDAO.sellingDrink(drinkId);
        List<Drink> drinkList = drinkDAO.selectDrinkOutOfStock();
        request.setAttribute("drinkList", drinkList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("resource/display/listDrinkUnLock.jsp");
        dispatcher.forward(request, response);
    }

    private void stopTrading(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        int drinkId = Integer.parseInt(request.getParameter("drinkId"));
        drinkDAO.stopSellingDrink(drinkId);
        List<Drink> drinkList = drinkDAO.selectDrinkInStock();
        request.setAttribute("drinkList", drinkList);
//        resp.sendRedirect(request.getContextPath() + "/drinks");
        RequestDispatcher dispatcher = request.getRequestDispatcher("resource/display/listDrink.jsp");
        dispatcher.forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createDrink(request, response);
                break;
            case "edit":
                editDrink(request, response);
                break;
            case "search":
                search(request, response);
                break;
        }
    }

    private void createDrink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("typeD");
        String drinkName = request.getParameter("drinkNameD");
        String messErrorName = "";
        if (drinkName == null || drinkName.equals("")) {
            messErrorName = "Không được để trống trường này";
        } else {
            if (drinkDAO.checkDuplicateDrinkName(drinkName)) {
                messErrorName = "Tên này đã tồn tại";
            }
        }

        String messErrorPrice = "";
        Integer price = null;
        long x = 10000000L;
        try {
            price = Integer.parseInt(request.getParameter("priceD"));
            if (price < 0 || price >= x) {
                messErrorPrice = "Vui lòng nhập giá lớn hơn 0 và bé hơn 10000000";
            }
        } catch (Exception e) {
            messErrorPrice = "Dữ liệu không hợp lệ, vui lòng nhập lại";
        }

        if (!messErrorName.equals("") || !messErrorPrice.equals("")) {
            request.setAttribute("drinkNameD", drinkName);
            request.setAttribute("priceD", price == null ? "" : price);
            request.setAttribute("messErrorName", messErrorName);
            request.setAttribute("messErrorPrice", messErrorPrice);
            RequestDispatcher dispatcher = request.getRequestDispatcher("resource/display/addDrink.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Drink newDrink = new Drink(type, drinkName.trim(), price);
        drinkDAO.addDrink(newDrink);
        response.sendRedirect(request.getContextPath() + "/drinks");
    }

    private void editDrink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int drinkId = Integer.parseInt(request.getParameter("drinkId"));
        String type = request.getParameter("typeD");
        String drinkName = request.getParameter("drinkNameD");
        String messErrorName = "";
        if (drinkName == null || drinkName.equals("")) {
            messErrorName = "Không được để trống trường này";
        } else {
            if (drinkDAO.checkDuplicateDrinkName(drinkName)) {
                messErrorName = "Tên này đã tồn tại";
            }
        }
        String messErrorPrice = "";
        Integer price = null;
        try {
            price = Integer.parseInt(request.getParameter("priceD"));
            if (price < 0) {
                messErrorPrice = "Vui lòng nhập giá lớn hơn 0";
            }
        } catch (Exception e) {
            messErrorPrice = "Dữ liệu không hợp lệ, vui lòng nhập lại";
        }
        if (!messErrorName.equals("") || !messErrorPrice.equals("")) {
            request.setAttribute("drinkNameD", drinkName);
            request.setAttribute("priceD", price == null ? "" : price);
            request.setAttribute("messErrorName", messErrorName);
            request.setAttribute("messErrorPrice", messErrorPrice);
            RequestDispatcher dispatcher = request.getRequestDispatcher("resource/display/editDrink.jsp");
            dispatcher.forward(request, response);
            return;
        }
        Drink editDrink = new Drink(drinkId, type, drinkName, price);
        drinkDAO.editDrink(editDrink);
        response.sendRedirect(request.getContextPath() + "/drinks");
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("nameSearch");
        List<Drink> drinks = drinkDAO.searchByName(name);
        request.setAttribute("drinkList", drinks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("resource/display/listDrink.jsp");
        dispatcher.forward(request, response);
    }

}
