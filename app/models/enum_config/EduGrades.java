package models.enum_config;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 02/04/2017.
 */
public enum  EduGrades {

    KJ_PRIMARY("KJ-PRIMARY"),
    HIGH_SCHOOL("HIGH-SCHOOL");

    private final String value;

    EduGrades(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static EduGrades getEduGradesValue(String v) {
        for (EduGrades e : EduGrades.values()) {
            if (e.value.equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
