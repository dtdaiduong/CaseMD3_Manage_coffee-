package DAO;

import com.models.Item;

import java.util.List;

public interface IServiceItemDAO {

    List<Item> selectAllItem();

    List<Item> selectAllItemLock();

    List<Item> selectAll();

    Item selectItem(int idItem);

    int createItem(Item item);

    boolean updateItem(Item item);

    Item lockItem(int id);

    Item unlockItem(int id);

    List<Item> shortByPriceItem();

    List<Item> searchByName(String name);

    boolean checkDuplicateNameItem(String nameItem);

}
