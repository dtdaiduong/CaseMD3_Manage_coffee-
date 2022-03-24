package dao;

import model.Drink;
import model.Status;
import model.User;
import utils.ConvertUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DrinkDAO implements IDrinkDAO {
    DecimalFormat formatter = new DecimalFormat("###,###,###" + "đ");
    private Connection connection = null;
    private String SELECTALLDRINK = "select * from drink;";
    private String SELECTALLUSER = "select * from user";
    private String SEARCH = "select * from coffee_Manage.drink where drinkName like ?;";
    private String SELECTACTIVE = "select * from drink where status ='1';";
    private String SELECTDEACTIVE = "select * from drink where status='0';";
    private String ADD = "insert into drink( type, drinkName, price, status) values(?, ?, ?, ?);";
    private String UPDATE = "UPDATE drink SET type=?, drinkName=?, price=?  WHERE drinkId=?;";
    private String SETSTATUS = "UPDATE drink SET status=? WHERE drinkId=?;";
    private String SHOWSTATUS = "select drink.status from drink where drinkId =?;";
    private String TRADING = "UPDATE drink SET status='1' WHERE drinkId=?;";


    @Override
    public boolean checkDuplicateDrinkName(String drinkName){
        List<Drink> drinks = selectAllDrink();
        for (Drink drink : drinks){
            if (drink.getDrinkNameD().toLowerCase().equals(drinkName.toLowerCase()))
                return true;
        }
        return false;
    }

    @Override
    public List<Drink> selectAllDrink() {
//        Status statusD = null;
        List<Drink> drinkList = new ArrayList<>();
        try (Connection connection = ConnectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECTACTIVE)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int drinkId = rs.getInt(1);
                String type = rs.getNString(2);
                String drinkName = rs.getString(3);
                int price = Integer.parseInt(rs.getString(4));
                int status = rs.getInt(5);
//                String statusD = Status.parseStatus(status);
                drinkList.add(new Drink(drinkId, type, drinkName, price, Status.parseStatus(status)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return drinkList;
    }

//    @Override
//    public List<User> selectAllUser() {
//        List<User> userList = new ArrayList<>();
//        try(Connection connection = ConnectionDAO.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECTALLUSER)) {
//            ResultSet rs = preparedStatement.executeQuery();
//
//        }
//    }

//    public static void main(String[] args) {
//        DrinkDAO drinkDAO = new DrinkDAO();
//        for (Drink drink : drinkDAO.selectAllDrink()) {
//            System.out.println(drink.toString());
//        }
//    }

//    @Override
//    public List<Drink> selectDrinkByName(String check) {
//        List<Drink> drinkList = new ArrayList<>();
//        Status statusD = null;
//        try {
//            connection = ConnectionDAO.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH);
//            preparedStatement.setString(1, "%" + check + "%");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int drinkId = resultSet.getInt("drinkId");
//                String type = resultSet.getString("type");
//                String drinkName = resultSet.getString("drinkName");
//                int price = Integer.parseInt(formatter.format(resultSet.getLong("price")));
//                int status = resultSet.getInt("status");
//                statusD = Status.parseStatus(status);
//                drinkList.add(new Drink(drinkId,type, drinkName, price, statusD));
//            }
//            preparedStatement.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return drinkList;
//    }

    @Override
    public List<Drink> searchByName(String drinkNameSearch){
        List<Drink> drinks = selectAllDrink();
        List<Drink> searchList = new ArrayList<>();
        for (Drink drink: drinks){
            if (ConvertUtils.removeAccent(drink.getDrinkNameD()).trim().toLowerCase().contains(ConvertUtils.removeAccent(drinkNameSearch).trim().toLowerCase()))
                searchList.add(drink);
        }
        return searchList;
    }


    @Override
    public List<Drink> selectDrinkInStock() {
        Status statusD;
        List<Drink> drinkList = new ArrayList<>();
        try {
            connection = ConnectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECTACTIVE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int drinkId = resultSet.getInt("drinkId");
                String type = resultSet.getString("type");
                String drinkName = resultSet.getString("drinkName");
                int price = resultSet.getInt("price");
                int status = resultSet.getInt("status");
                statusD = Status.parseStatus(status);
                drinkList.add(new Drink(drinkId, type, drinkName, price, statusD));

            }
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return drinkList;
    }

    @Override
    public List<Drink> selectDrinkOutOfStock() {
        Status statusD;
        List<Drink> drinkList = new ArrayList<>();
        try {
            connection = ConnectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECTDEACTIVE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int drinkId = rs.getInt("drinkId");
                String type = rs.getString("type");
                String drinkName = rs.getString("drinkName");
                int price = rs.getInt("price");
                int status = rs.getInt("status");
                statusD = Status.parseStatus(status);
                drinkList.add(new Drink(drinkId, type, drinkName, price, statusD));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return drinkList;
    }

    @Override
    public Drink selectDrink(int drinkId) {
        List<Drink> drinkList = selectAllDrink();
        for (Drink drink : drinkList) {
            if (drink.getDrinkIdD() == drinkId) {
                return drink;
            }
        }
        return null;
    }

    @Override
    public void addDrink(Drink newDrink) {
        try {
            connection = ConnectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD);
            System.out.println(newDrink);
            preparedStatement.setString(1, newDrink.getTypeD());
            preparedStatement.setString(2, newDrink.getDrinkNameD());
            preparedStatement.setInt(3, newDrink.getPriceD());
            preparedStatement.setInt(4,1);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        DrinkDAO drinkDAO = new DrinkDAO();
//        Drink drink = new Drink("cafe","cafe","15000");
//        drinkDAO.addDrink(drink);
//    }
    @Override
    public void editDrink(Drink editDrink) {
        try {
            connection = ConnectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, editDrink.getTypeD());
            preparedStatement.setString(2, editDrink.getDrinkNameD());
            preparedStatement.setInt(3, editDrink.getPriceD());
            preparedStatement.setInt(4, editDrink.getDrinkIdD());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void stopSellingDrink(int drinkId) {
        try {
            connection = ConnectionDAO.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(SETSTATUS);
            PreparedStatement check = connection.prepareStatement(SHOWSTATUS);
            check.setInt(1, drinkId);
            ResultSet resultSet = check.executeQuery();
            while (resultSet.next()) {
                int statusD = resultSet.getInt("status");
                int newStatus = statusD == 1 ? 0 : 1;
                preparedStatement.setInt(1, newStatus);
                preparedStatement.setInt(2, drinkId);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            check.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sellingDrink(int drinkId) {
        try {
            connection = ConnectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TRADING);
            preparedStatement.setInt(1, drinkId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
