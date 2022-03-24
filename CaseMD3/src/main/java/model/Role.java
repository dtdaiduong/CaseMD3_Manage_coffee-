package model;

public enum Role {
    ADMIN(0), USER(1);
    private int value;

    Role(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role parseRole(int value) {
        Role[] values = values();
        for (Role rl : values) {
            if (rl.value == value)
                return rl;
        }
        throw new IllegalArgumentException("value position invalid");
    }
}
