package models.enum_config;

/**
 * Created by Ibrahim Olanrewaju  Abdulsemiu on 14/08/2017.
 */
public enum PeriodMode {

    TEACHING("Teaching"),
    TEST("Test"),
    REVISION("Revision"),
    EXAMINATION("Examination"),
    BRAKE("Brake");

    private final String value;

    PeriodMode(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static PeriodMode periodModeGetValue(String v) {
        for (PeriodMode p : PeriodMode.values()) {
            if (p.value.equals(v)) {
                return p;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
