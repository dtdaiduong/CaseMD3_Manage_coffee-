package DAO;

import com.models.Item;
import com.models.OrderDetail;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DetailOrderDAO implements IOrderDetailDAO {
    private ServiceItemDAO serviceItemDAO = new ServiceItemDAO();
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quan_ly_cafe_md3?useSSL=false";
    private final String jdbcUserName = "root";
    private final String jdbcPassword = "rim0847990907";
    private final String SQL_SELECT_ORDERDETAIL = "select * from orderdetail where idOrderItem = ?";
    private final String SQL_SELECT_DETAIL = "select * from orderdetail where idOrderItem = ? and idItem = ?";
    private final String SQL_ADD_PRODUCT = "insert into orderDetail values (?,?,?,?,?); ";
    private final String SQL_UPDATE_PRODUCT = "update orderDetail set quantity = ?, totalPrice = ? where idOrderItem = ? and idItem = ?;";
    private final String SQL_REMOVE_PRODUCT = "delete from orderDetail where idOrderItem = ? and idItem = ?;";
    private final String SQL_TOTAL_ORDER = "select sum(totalPrice)  from orderDetail where idOrderItem = ?  group by idOrderItem;";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<OrderDetail> selectOrderDetail(int idOrder) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERDETAIL);
        ) {
            preparedStatement.setInt(1, idOrder);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOrderSelect = rs.getInt(1);
                int idItem = rs.getInt(2);
                int price = rs.getInt(3);
                int quantity = rs.getInt(4);
                int total = rs.getInt(5);
                OrderDetail orderDetail = new OrderDetail(idOrderSelect, idItem, price, quantity, total);
                orderDetails.add(orderDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public OrderDetail selectDetail(int idOrder, int idItem) {
        OrderDetail orderDetail = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DETAIL);
        ) {
            preparedStatement.setInt(1, idOrder);
            preparedStatement.setInt(2, idItem);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOrderDetail = rs.getInt(1);
                int idItemDetail = rs.getInt(2);
                int price = rs.getInt(3);
                int quantity = rs.getInt(4);
                int totalPrice = rs.getInt(5);
                orderDetail = new OrderDetail(idOrderDetail, idItemDetail, price, quantity, totalPrice);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    @Override
    public int addProduct(int idOrder, int idItem) {
        Item item = serviceItemDAO.selectItem(idItem);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PRODUCT);
        ) {
            preparedStatement.setInt(1, idOrder);
            preparedStatement.setInt(2, idItem);
            preparedStatement.setInt(3, item.getPrice());
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, item.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }

    public static void main(String[] args) {
        DetailOrderDAO detailOrderDAO = new DetailOrderDAO();
        detailOrderDAO.updateProduct(36, 1);
//        detailOrderDAO.removeOneProduct(1, 1);
//        detailOrderDAO.addProduct(2,2);
//        detailOrderDAO.addProduct(2,5);
//        detailOrderDAO.addProduct(3,8);
        for (OrderDetail orderDetail : detailOrderDAO.selectOrderDetail(36)) {
            System.out.println(orderDetail.toString());
        }
//        System.out.println(detailOrderDAO.selectDetail(1, 1));
//       int totalOrder =  detailOrderDAO.totalOrder(2);
//        System.out.println(totalOrder);
    }

    @Override
    public boolean updateProduct(int idOrder, int idItem) {
        OrderDetail orderDetail = selectDetail(idOrder, idItem);
        int quantityNew = orderDetail.getQuantity() + 1;
        int totalPriceNew = orderDetail.getPriceItem() * quantityNew;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
        ) {
            preparedStatement.setInt(1, quantityNew);
            preparedStatement.setInt(2, totalPriceNew);
            preparedStatement.setInt(3, idOrder);
            preparedStatement.setInt(4, idItem);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeOneProduct(int idOrder, int idItem) {
        OrderDetail orderDetail = selectDetail(idOrder, idItem);
        int quantityNew = orderDetail.getQuantity() - 1;
        int totalPriceNew = orderDetail.getPriceItem() * quantityNew;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
        ) {
            preparedStatement.setInt(1, quantityNew);
            preparedStatement.setInt(2, totalPriceNew);
            preparedStatement.setInt(3, idOrder);
            preparedStatement.setInt(4, idItem);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean removeProduct(int idOrder, int idItem) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_PRODUCT);
        ) {
            preparedStatement.setInt(1, idOrder);
            preparedStatement.setInt(2, idItem);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int totalOrder(int idOrder) {
        int totalOrder = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_TOTAL_ORDER);
        ) {
            preparedStatement.setInt(1, idOrder);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                totalOrder = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalOrder;
    }

    @Override
    public boolean checkIdItem(int idOrder, int idItem) {
        for (OrderDetail detail : selectOrderDetail(idOrder)) {
            if (detail.getIdItem() == idItem) {
                return true;
            }
        }
        return false;
    }


}
