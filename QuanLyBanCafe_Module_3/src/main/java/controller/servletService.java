package controller;

import DAO.DetailOrderDAO;
import DAO.OrderItemDAO;
import DAO.ServiceItemDAO;
import com.models.Item;
import com.models.Order;
import com.models.OrderDetail;
import com.models.Status;
import utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/servletController")
public class servletService extends HttpServlet {
    private ServiceItemDAO serviceItemDAO = new ServiceItemDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();
    private DetailOrderDAO detailOrderDAO = new DetailOrderDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createItem(req, resp);
                break;
            case "lock":
                lockItem(req, resp);
                break;
            case "update":
                updateItem(req, resp);
                break;
            case "search":
                searchItemByName(req, resp);
                break;
            case "showProductLock":
                showProductLock(req, resp);
                break;
            case "unlock":
                unlockProduct(req, resp);
                break;
            case "showOrder":
                showOrder(req, resp);
                break;
            case "addProduct":
                viewAddItem(req, resp);
                break;
            case "remove":
                viewRemoveItem(req, resp);
                break;
            case "cancelOrder":
                cancelOrder(req, resp);
                break;
            case "showDetailOrder":
                showDetailOrder(req, resp);
                break;
            case "showDasBoard" :
                showDasBoard(req, resp);
            default:
//
                showProduct(req, resp);
                break;
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Item> items = serviceItemDAO.selectAllItem();
        request.setAttribute("listProduct", items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/managerItem.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createItem(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/viewCreateItem.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void lockItem(HttpServletRequest request, HttpServletResponse response) {
        int idLock = Integer.parseInt(request.getParameter("idItem"));
        String mess = "";
        List<Item> listProduct = serviceItemDAO.selectAllItem();
        if(orderItemDAO.checkOrder(idLock)) {
            mess = "Sản phẩm đang được Order, không thể khóa được!!!";
            request.setAttribute("mess",mess);
            request.setAttribute("listProduct",listProduct);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/managerItem.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            serviceItemDAO.lockItem(idLock);
            showProduct(request, response);
        }

    }

    private Item updateItem(HttpServletRequest request, HttpServletResponse response) {
        int idItem = Integer.parseInt(request.getParameter("idItem"));
        Item itemUpdate = serviceItemDAO.selectItem(idItem);
        String nameItem = itemUpdate.getName();
        int price = itemUpdate.getPrice();
        String description = itemUpdate.getDescription();
        String status = itemUpdate.getStatus().getValue();
        String messWarning = "";
        List<Item> listProduct = serviceItemDAO.selectAllItem();
        if(orderItemDAO.checkOrder(idItem)) {
            messWarning = "Sản phẩm đang được Order, không thể update";
            request.setAttribute("messWarning",messWarning);
            request.setAttribute("listProduct",listProduct);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/managerItem.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else {
            request.setAttribute("idItem", idItem);
            request.setAttribute("nameItem", nameItem);
            request.setAttribute("price", price);
            request.setAttribute("description", description);
            request.setAttribute("status", status);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/viewUpdateItem.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return itemUpdate;
        }

    }

    private void searchItemByName(HttpServletRequest request, HttpServletResponse response) {
        String nameSearch = request.getParameter("search");
        List<Item> listProduct = serviceItemDAO.searchByName(nameSearch);
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/managerItem.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showProductLock(HttpServletRequest request, HttpServletResponse response) {
        List<Item> listProductLock = serviceItemDAO.selectAllItemLock();
        request.setAttribute("listProductLock", listProductLock);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/viewProductLock.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void unlockProduct(HttpServletRequest request, HttpServletResponse response) {
        int idItem = Integer.parseInt(request.getParameter("idItem"));
        serviceItemDAO.unlockItem(idItem);
        showProductLock(request, response);
    }

    private void showOrder(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = orderItemDAO.selectAllOderItem();
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewOrder/listOrder.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showViewCreateOrder(HttpServletRequest request, HttpServletResponse response, int idOrder) {
        List<Item> listProduct = serviceItemDAO.selectAllItem();
        request.setAttribute("idOrder", idOrder);
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewOrder/viewCreateOrder.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void viewAddItem(HttpServletRequest request, HttpServletResponse response) {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        int idItem = Integer.parseInt(request.getParameter("idItem"));
        List<OrderDetail> details = detailOrderDAO.selectOrderDetail(idOrder);
        List<Item> listProduct = serviceItemDAO.selectAllItem();
        if (details.size() == 0) {
            detailOrderDAO.addProduct(idOrder, idItem);
        } else if (detailOrderDAO.checkIdItem(idOrder, idItem)) {
            detailOrderDAO.updateProduct(idOrder, idItem);
        } else {
            detailOrderDAO.addProduct(idOrder, idItem);

        }
        details = detailOrderDAO.selectOrderDetail(idOrder);
        int totalPrice = detailOrderDAO.totalOrder(idOrder);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("idOrder", idOrder);
        request.setAttribute("details", details);
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewOrder/viewCreateOrder.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewRemoveItem(HttpServletRequest request, HttpServletResponse response) {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        int idItem = Integer.parseInt(request.getParameter("idItem"));
        List<Item> listProduct = serviceItemDAO.selectAllItem();
        if (detailOrderDAO.selectDetail(idOrder, idItem).getQuantity() == 1) {
            detailOrderDAO.removeProduct(idOrder, idItem);
        } else if (detailOrderDAO.checkIdItem(idOrder, idItem)) {
            detailOrderDAO.removeOneProduct(idOrder, idItem);
        }
        List<OrderDetail> details = detailOrderDAO.selectOrderDetail(idOrder);
        int totalPrice = detailOrderDAO.totalOrder(idOrder);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("idOrder", idOrder);
        request.setAttribute("details", details);
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewOrder/viewCreateOrder.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cancelOrder(HttpServletRequest request, HttpServletResponse response) {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        orderItemDAO.deleteOrder(idOrder);
        showOrder(request, response);
    }

    private void showDetailOrder(HttpServletRequest request, HttpServletResponse response) {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        List<OrderDetail> details = detailOrderDAO.selectOrderDetail(idOrder);
        Order order = orderItemDAO.selectOrder(idOrder);
//        ServiceItemDAO serviceItemDAO = new ServiceItemDAO();
        request.setAttribute("details", details);
        request.setAttribute("serviceItemDAO", serviceItemDAO);
        request.setAttribute("order",order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewOrder/detailOrder.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDasBoard(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewAdmin/dasboard.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        switch (action) {
            case "create":
                create(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "createOrder":
                createOrder(req, resp);
                break;
            case "paidBill":
                paidBill(resp, req);
                break;
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nameItem = request.getParameter("nameItem");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String messErrorName = "";
        String messErrorPrice = "";
        if (nameItem == null || nameItem.equals("")) {
            messErrorName = "Không được để trống trường này";
        }
        if(serviceItemDAO.checkDuplicateNameItem(nameItem)) {
            messErrorName = "Tên này đã được sử dụng";
        }
        if (price == null || price.equals("")) {
            messErrorPrice = "Trường này không dược bỏ trống";
        } else if (!ValidateUtils.isNumber(price)) {
            messErrorPrice = "Dữ liệu không hợp lệ, vui lòng nhập lại";
        }

        if (!messErrorName.equals("") || !messErrorPrice.equals("")) {
            request.setAttribute("nameItem", nameItem);
            request.setAttribute("price", price);
            request.setAttribute("description", description);
            request.setAttribute("messErrorName", messErrorName);
            request.setAttribute("messErrorPrice", messErrorPrice);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/viewCreateItem.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } else {
            int priceNew = Integer.parseInt(price);
            Item newItem = new Item(nameItem, priceNew, description, Status.parseRole(status));
            serviceItemDAO.createItem(newItem);
            String messSuccess = "Bạn đã thêm sản phẩm " + newItem.getName() + " thành công!!!";
            request.setAttribute("messSuccess", messSuccess);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/viewCreateItem.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
//            createItem(request, response);
        }


    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int idItem = Integer.parseInt(request.getParameter("idItem"));
        String nameItem = request.getParameter("nameItem");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String messErrorName = "";
        String messErrorPrice = "";
        if (nameItem == null || nameItem.equals("")) {
            messErrorName = "Không được để trống trường này";
        }
        if(serviceItemDAO.checkDuplicateNameItem(nameItem) && !nameItem.equals(serviceItemDAO.selectItem(idItem).getName())) {
            messErrorName = "Tên này đã được sử dụng";
        }
        if (price == null || price.equals("")) {
            messErrorPrice = "Trường này không dược bỏ trống";
        } else if (!ValidateUtils.isNumber(price)) {
            messErrorPrice = "Dữ liệu không hợp lệ, vui lòng nhập lại";
        }

        if (!messErrorName.equals("") || !messErrorPrice.equals("")) {
            request.setAttribute("nameItem", nameItem);
            request.setAttribute("price", price);
            request.setAttribute("description", description);
            request.setAttribute("status", status);
            request.setAttribute("messErrorName", messErrorName);
            request.setAttribute("messErrorPrice", messErrorPrice);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/viewUpdateItem.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            int priceUpdate = Integer.parseInt(price);
            Item itemUpdate = new Item(idItem, nameItem, priceUpdate, description, Status.parseRole(status));
            serviceItemDAO.updateItem(itemUpdate);
            List<Item> listProduct = serviceItemDAO.selectAllItem();
            String messSuccess = "Bạn đã cập nhật sản phẩm " + itemUpdate.getName() + " thành công!!!";
            request.setAttribute("listProduct",listProduct);
            request.setAttribute("messSuccess", messSuccess);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/viewManagerItem/managerItem.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) {
        Order newOrder = new Order(new Date());
        int id = orderItemDAO.createOrder(newOrder);
        showViewCreateOrder(request, response, id);

    }

    private void paidBill(HttpServletResponse response, HttpServletRequest request) {
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        orderItemDAO.updateOder(idOrder, totalPrice);
        showOrder(request, response);
    }


}
