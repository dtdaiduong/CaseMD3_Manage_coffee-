package DAO;

import com.models.User;

import java.util.List;

public interface IServiceUser {
    List<User> selectAllUser();
    List<User> selectAllUserActive();
    List<User> selectAllUserLock();
    User selectUser(int idUser);
    int createUser(User user);
    boolean updateUser(User user);
    boolean lockUser(int idUser);
    boolean unlockUser(int idUser);
    boolean checkDuplicateName(String name);
    boolean checkDuplicatePhone(String phone);
    boolean checkDuplicateEmail(String email);
    boolean checkDuplicateUserName(String userName);
    boolean checkDuplicatePassword(String password);
    boolean checkAge(int age);
}
