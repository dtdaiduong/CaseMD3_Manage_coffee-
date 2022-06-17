package DAO;

import com.models.Role;
import com.models.Status;
import com.models.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IServiceUser {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quan_ly_cafe_md3?useSSL=false";
    private final String jdbcUserName = "root";
    private final String jdbcPassword = "rim0847990907";
    private final String SQL_SELECT_ALL_USER = "select * from user;";
    private final String SQL_SELECT_ALL_USER_ACTIVE = "select * from user where status = ACTIVE";
    private final String SQL_SELECT_ALL_USER_LOCK = "select * from user where status = LOCK";
    private final String SQL_SELECT_USER = "select * from user where idUser = ?";
    private final String SQL_INSERT_USER = "insert into user (nameUser,userName,password, phone, email,status,role,age) values (?,?,?,?,?,?,?,?)";


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
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt("idUser");
                String nameUser = rs.getString("nameUser");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String status = rs.getString("status");
                String role = rs.getString("role");
                User user = new User(idUser, nameUser, age, userName, password, phone, email, Role.parseRole(role), Status.parseRole(status));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> selectAllUserActive() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER_ACTIVE)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt("idUser");
                String nameUser = rs.getString("nameUser");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String status = rs.getString("status");
                String role = rs.getString("role");
                User user = new User(idUser, nameUser, age, userName, password, phone, email, Role.parseRole(role), Status.parseRole(status));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> selectAllUserLock() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER_LOCK)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt("idUser");
                String nameUser = rs.getString("nameUser");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String status = rs.getString("status");
                String role = rs.getString("role");
                User user = new User(idUser, nameUser, age, userName, password, phone, email, Role.parseRole(role), Status.parseRole(status));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User selectUser(int idUser) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER)
        ) {
            preparedStatement.setInt(1,idUser);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String nameUser = rs.getString("nameUser");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String status = rs.getString("status");
                String role = rs.getString("role");
                user = new User(idUser, nameUser, age, userName, password, phone, email, Role.parseRole(role), Status.parseRole(status));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int createUser(User user) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
        ){
            preparedStatement.setString(1,user.getNameUser());
            preparedStatement.setString(2,user.getUserName());
            preparedStatement.setString(3,user.getPassWord());
            preparedStatement.setString(4,user.getPhone());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setString(6,user.getStatus().getValue());
            preparedStatement.setString(7,user.getRole().getValue());
            preparedStatement.setInt(8,user.getAge());
//            ResultSet rs = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean lockUser(int idUser) {
        return false;
    }

    @Override
    public boolean unlockUser(int idUser) {
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        return false;
    }

    @Override
    public boolean checkDuplicatePhone(String phone) {
        return false;
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        return false;
    }

    @Override
    public boolean checkDuplicateUserName(String userName) {
        return false;
    }

    @Override
    public boolean checkDuplicatePassword(String password) {
        return false;
    }

    @Override
    public boolean checkAge(int age) {
        return false;
    }
}
