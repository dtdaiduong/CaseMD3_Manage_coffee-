package model;

public class Drink {
    private int drinkIdD;
    private String typeD;
    private String drinkNameD;
    private int priceD;
    private Status statusD;

    public Drink() {
    }

    public Drink(int drinkIdD, String typeD, String drinkNameD, int priceD, Status statusD) {
        this.drinkIdD = drinkIdD;
        this.typeD = typeD;
        this.drinkNameD = drinkNameD;
        this.priceD = priceD;
        this.statusD = statusD;
    }

    public Drink(int drinkIdD, int priceD, String drinkNameD, Status statusD) {
        this.drinkIdD = drinkIdD;
        this.priceD = priceD;
        this.drinkNameD = drinkNameD;
        this.statusD = statusD;
    }

    public Drink(int drinkIdD, String typeD, String drinkNameD, int priceD) {
        this.drinkIdD = drinkIdD;
        this.typeD = typeD;
        this.drinkNameD = drinkNameD;
        this.priceD = priceD;
    }

    public Drink(String typeD, String drinkNameD, int priceD) {
        this.typeD = typeD;
        this.drinkNameD = drinkNameD;
        this.priceD = priceD;
    }

    public int getDrinkIdD() {
        return drinkIdD;
    }

    public void setDrinkIdD(int drinkIdD) {
        this.drinkIdD = drinkIdD;
    }

    public String getTypeD() {
        return typeD;
    }

    public void setTypeD(String typeD) {
        this.typeD = typeD;
    }

    public String getDrinkNameD() {
        return drinkNameD;
    }

    public void setDrinkNameD(String drinkNameD) {
        this.drinkNameD = drinkNameD;
    }

    public int getPriceD() {
        return priceD;
    }

    public void setPriceD(int priceD) {
        this.priceD = priceD;
    }

    public Status getStatusD() {
        return statusD;
    }

    public void setStatusD(Status statusD) {
        this.statusD = statusD;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "drinkId=" + drinkIdD +
                ", drinkName='" + drinkNameD + '\'' +
                ", price='" + priceD + '\'' +
                ", status=" + statusD +
                '}';
    }
}
