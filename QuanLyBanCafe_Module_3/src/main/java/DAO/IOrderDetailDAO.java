package DAO;

import com.models.OrderDetail;

import java.util.List;

public interface IOrderDetailDAO {

    List<OrderDetail> selectOrderDetail(int idOrder);

    OrderDetail selectDetail(int idOrder, int idItem);

    int addProduct(int idOrder, int idItem);

    boolean updateProduct(int idOrder, int idItem);

    boolean removeOneProduct(int idOrder, int idItem);

    boolean removeProduct(int idOrder, int idItem);

    int totalOrder(int idOrder);

    boolean checkIdItem(int idOrder, int idItem);

}
