package model;

public enum Status {
    OUT_OF_STOCK(0),
    STOCKING(1);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Status parseStatus(int value) {
        Status[] values = values();
        for (Status st : values) {
            if (st.value == value)
                return st;
        }
        throw new IllegalArgumentException("value position invalid");
    }
}
