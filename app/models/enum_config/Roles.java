package models.enum_config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S  on 28/12/2016.
 */
public enum Roles {
    TEACH_STAFF("TeachStaff"),
    NON_TEACH_STAFF("NonTeachStaff"),
    ADMIN("Admin"),
    IT_MANAGER("ItManager"),
    SUPPER_ADMIN("SupperAdmin"),
    STUDENT("Student"),
    GUARDIAN("Guardian"),
    DEVELOPER("Developer");


    private final String value;

    Roles(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static Roles roleGetValue(String v) {
        for (Roles r : Roles.values()) {
            if (r.value.equals(v)) {
                return r;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static List<Roles> getAllRoleValues(){
        List<Roles> roles = new ArrayList<>();
        for(Roles r : Roles.values()){
            roles.add(r);
        }
        return roles;
    }
}
