package models.enum_config;

/**
 * Created by Ibrahim Olanrewaju S on 29/04/2017.
 */
public enum LockAndUnlock {

    UNLOCK("Unlock"),
    LOCK("Lock");

    private final String value;

    LockAndUnlock(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static LockAndUnlock getLockValue(String v) {
        for (LockAndUnlock l : LockAndUnlock.values()) {
            if (l.value.equals(v)) {
                return l;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
