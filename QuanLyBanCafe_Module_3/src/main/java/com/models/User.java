package com.models;

public class User {
    private int idUser;
    private String nameUser;
    private int age;
    private String userName;
    private String passWord;
    private String phone;
    private String email;
    private Role role;
    private Status status;

    public User() {
    }

    public User(String nameUser, int age, String userName, String passWord, String phone, String email, Role role, Status status) {
        this.nameUser = nameUser;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public User(int idUser, String nameUser, int age, String userName, String passWord, String phone, String email, Role role, Status status) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.age = age;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nameUser='" + nameUser + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
