package dao;

import model.Drink;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IDrinkDAO {


    boolean checkDuplicateDrinkName(String drinkName);

    List<Drink> selectAllDrink();

//    List<Drink> selectDrinkByName(String check);

    List<User> selectAllUser() throws SQLException;

    List<Drink> searchByName(String drinkNameSearch);

    List<Drink> selectDrinkInStock();

    List<Drink> selectDrinkOutOfStock();

    Drink selectDrink(int drinkId);

    void addDrink(Drink newDrink);

    void editDrink(Drink editDrink);

    void stopSellingDrink(int id);

    void sellingDrink(int id);
}
