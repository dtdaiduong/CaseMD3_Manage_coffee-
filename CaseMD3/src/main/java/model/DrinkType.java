package model;

public enum DrinkType {
    CAFE("CAFE"), TEA("TEA"), YOGURT("YOGURT"), JUICE("JUICE"), SMOOTHIE("SMOOTHIE"), OTHER("OTHER");
    private String value;

    DrinkType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public static boolean fromValue(String value) {
        DrinkType[] values = values();
        for (DrinkType drinkType : values) {
            if (drinkType.value.equals(value))
                return true;
        }

        return false;
    }

    public String contains(String type) {
        return type;
    }

}
