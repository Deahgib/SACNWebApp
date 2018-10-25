package webapp.backend;

import java.util.HashMap;
import java.util.Map;

public enum PatchIdentity{
    EMPTY(0),
    INTENSITY(1),
    RED(2),
    BLUE(3),
    GREEN(4),
    CYAN(5),
    PURPLE(6),
    YELLOW(7),
    WHITE(8),
    STROBE(9),
    PAN(10),
    TITLT(11);


    private int value;

    PatchIdentity(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
