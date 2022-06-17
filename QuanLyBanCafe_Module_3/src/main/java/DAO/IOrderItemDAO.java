package DAO;

import com.models.Order;

import java.util.List;

public interface IOrderItemDAO {

    List<Order> selectAllOderItem();

    Order selectOrder(int orderId);

    int createOrder(Order order);

    boolean updateOder(int idOrder, int totalPrice);

    boolean deleteOrder(int orderId);

    Order selectOrdering();

    boolean checkOrder(int idItem);
}
