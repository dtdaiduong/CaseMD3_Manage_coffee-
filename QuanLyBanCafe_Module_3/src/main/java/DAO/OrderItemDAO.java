package DAO;

import com.models.Order;
import com.models.OrderDetail;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

public class OrderItemDAO implements IOrderItemDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quan_ly_cafe_md3?useSSL=false";
    private final String jdbcUserName = "root";
    private final String jdbcPassword = "rim0847990907";
    private final String SQL_SELECT_ALL = "SELECT * FROM orderitem;";
    private final String SQL_SELECT_ORDER = "select * from orderitem where idOrderItem = ?";
    private final String SQL_INSERT_ORDER = "insert into orderitem (totalPrice,timeOrder) values (?,?);";
    private final String SQL_UPDATE_ORDER = "update orderItem set totalPrice = ? where idOrderItem = ?;";
    private final String SQL_DELETE_ORDER = "call deleteOrder(?)";
    private final String SQL_SELECT_ORDERING = "select * from orderItem where totalPrice = 0;";

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

    public static void main(String[] args) {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        orderItemDAO.deleteOrder(55);
    }

    @Override
    public List<Order> selectAllOderItem() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOrder = rs.getInt(1);
                int totalPrice = rs.getInt(2);
                Date timeOrder = new Date(rs.getTimestamp(3).getTime());
                Order order = new Order(idOrder, totalPrice, timeOrder);
                orders.add(order);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order selectOrder(int orderId) {
        Order order = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER);
        ) {
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOrder = rs.getInt(1);
                int totalPrice = rs.getInt(2);
                Date timeOrder = new Date(rs.getTimestamp(3).getTime());
                order = new Order(idOrder, totalPrice, timeOrder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public int createOrder(Order order) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, order.getTotalPrice());
            System.out.println(order.getTimeOrder());
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateOder(int idOrder, int totalPrice) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER);
        ) {
            preparedStatement.setInt(1, totalPrice);
            preparedStatement.setInt(2, idOrder);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(SQL_DELETE_ORDER);
        ) {
            callableStatement.setInt(1, orderId);
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order selectOrdering() {
        Order order = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERING)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOrder = rs.getInt(1);
                int totalPrice = rs.getInt(2);
                Date timeOrder = new Date(rs.getTimestamp(3).getTime());
                order = new Order(idOrder, totalPrice, timeOrder);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean checkOrder(int idItem) {
        DetailOrderDAO detailOrderDAO = new DetailOrderDAO();
        Order orders = selectOrdering();
        if(orders == null) {
            return false;
        } else {
            List<OrderDetail> orderDetails = detailOrderDAO.selectOrderDetail(orders.getOrderId());
            for (OrderDetail orderDetail : orderDetails) {
                if (orderDetail.getIdItem() == idItem) {
                    return true;
                }
            }
        }
        return false;
    }
}
