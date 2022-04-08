package models.enum_config;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 02/04/2017.
 */
public enum AutoConfigAuthenticate {
//school-configuration-code-002
    //school-code-002 Authenticate

    SCHOOL_CONFIG_ID("school-configuration-code-002"),
    SCHOOL_ID("school-code-002");

    private final String value;

    AutoConfigAuthenticate(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static AutoConfigAuthenticate getConfigAuthValue(String v) {
        for (AutoConfigAuthenticate a : AutoConfigAuthenticate.values()) {
            if (a.value.equals(v)) {
                return a;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
