package dao_implimentation;

/**
 * Created by TRAVELDEN DEV on 22/04/2017.
 */
public enum Collections {

    School("school"),
    Users("users"),
    Staff("staff"),
    Guardian("guardian"),
    Feature("feature"),
    AppsPrivilege("appsPrivilege"),
    SchoolConfig("schoolConfig")
    ;


    private final String value;

    Collections(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static Collections conGetValue(String v) {
        for (Collections c : Collections.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
