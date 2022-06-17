package DAO;

import com.models.Item;
import com.models.Status;
import utils.ShortByPriceASC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceItemDAO implements IServiceItemDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quan_ly_cafe_md3?useSSL=false";
    private final String jdbcUserName = "root";
    private final String jdbcPassword = "rim0847990907";
    private final String SQL_SELECT_ALL_ITEM = "select * from item where statusItem = 'ACTIVE'";
    private final String SQL_SELECT_ALL_ITEM_LOCK = "select * from item where statusItem = 'LOCK'";
    private final String SQL_SELECT_ITEM = "select * from item where idItem = ?";
    private final String SQL_INSERT_ITEM = "insert into item (nameItem,price,descriptionItem,statusItem) values (?,?,?,?);";
    private final String SQL_UPDATE_ITEM = "update item set nameItem = ?, price = ?, descriptionItem = ?, statusItem = ? where idItem =?;";
    private final String SQL_LOCK_ITEM = "update item set  statusItem = 'LOCK' where idItem = ?;";
    private final String SQL_UNLOCK_ITEM = "update item set  statusItem = 'ACTIVE' where idItem = ?;";
    private final String SQL_SELECT_ALL = "select * from item";
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
    public List<Item> selectAllItem() {
        List<Item> items = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ITEM);
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idItem = resultSet.getInt("idItem");
                String nameItem = resultSet.getString("nameItem");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("descriptionItem");
                String status = resultSet.getString("statusItem");
                Item item = new Item(idItem, nameItem, price, description, Status.parseRole(status));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> selectAllItemLock() {
        List<Item> itemsLock = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ITEM_LOCK)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idItem");
                String name = rs.getString("nameItem");
                int price = rs.getInt("price");
                String description = rs.getString("descriptionItem");
                String status = rs.getString("statusItem");
                Item lockItem = new Item(id,name,price,description,Status.parseRole(status));
                itemsLock.add(lockItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemsLock;
    }

    @Override
    public List<Item> selectAll() {
        List<Item> items = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
        ){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idItem = rs.getInt("idItem");
                String nameItem = rs.getString("nameItem");
                int price = rs.getInt("price");
                String description = rs.getString("descriptionItem");
                String status = rs.getString("statusItem");
                Item item = new Item(idItem, nameItem, price, description, Status.parseRole(status));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

//    public static void main(String[] args) {
//        ServiceItemDAO serviceItemDAO = new ServiceItemDAO();
//        Item itemNew = new Item(6,"Coffee black", 1000000, "super good", Status.ACTIVE);
////        int id = serviceItemDAO.createItem(itemNew);
//        serviceItemDAO.updateItem(itemNew);
//        Item itemLock = serviceItemDAO.unlockItem(2);
////        System.out.println(itemLock.toString());
//        List<Item> items = serviceItemDAO.selectAllItem();
//        for (Item item : items) {
//            System.out.println(item.toString());
//        }
//        System.out.println("-----------------------------------------");
//        for(Item item: serviceItemDAO.searchByName("f")) {
//            System.out.println(item.toString());
//        }
//
//    }

    @Override
    public Item selectItem(int idItem) {
        Item item = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM);
        ) {
            preparedStatement.setInt(1, idItem);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nameItem = resultSet.getString("nameItem");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("descriptionItem");
                String status = resultSet.getString("statusItem");
                item = new Item(idItem, nameItem, price, description, Status.parseRole(status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public int createItem(Item item) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getPrice());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setString(4, item.getStatus().getValue());
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
    public boolean updateItem(Item item) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ITEM);
        ) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getPrice());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setString(4, item.getStatus().getValue());
            preparedStatement.setInt(5, item.getIdItem());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Item lockItem(int id) {
        Item item = selectItem(id);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOCK_ITEM);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item unlockItem(int id) {
        Item item = selectItem(id);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNLOCK_ITEM);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<Item> shortByPriceItem() {
        List<Item> items = selectAllItem();
        items.sort(new ShortByPriceASC());
        return items;
    }

    @Override
    public List<Item> searchByName(String name) {
        List<Item> items = selectAllItem();
        List<Item> searchList = new ArrayList<>();

        for (Item item: items) {
            if(item.getName().toLowerCase().contains(name.toLowerCase())){
                searchList.add(item);
            }
        }
        return searchList;
    }

    @Override
    public boolean checkDuplicateNameItem(String nameItem) {
        List<Item> items = selectAll();
        for (Item item : items) {
            if(item.getName().toLowerCase().equals(nameItem.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
