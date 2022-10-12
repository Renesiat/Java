package step.learning.serial;

import java.io.Serializable;

public class DataObject implements Serializable {
    private int privateField;
    protected double protectedField;
    public String publicField;
    private transient int transField;

    public DataObject(Object... args) {
        privateField = args.length > 0 ? (int) args[0] : -1;
        protectedField = args.length > 1 ? (double) args[1] : -1;
        publicField = args.length > 2 ? (String) args[2] : "-";
        transField = args.length > 3 ? (int) args[3] : -1;
    }

    @Override
    public String toString() {
        return String.format("{privateField:%d, protectedField: %f, publicField:'%s', transField: %d}",
                privateField, protectedField, publicField, transField);

    }
}
