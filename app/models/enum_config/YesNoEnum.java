package models.enum_config;

import com.avaje.ebean.annotation.EnumValue;

/**
 * Created by TRAVELDEN DEV on 24/02/2017.
 */
public enum YesNoEnum {

    /*Yes("yes"),No("No");
    String s;
    YesNoEnum(String s){this.s = s;}
    public String getValue(){ return  s;}*/
    @EnumValue("Yes")
    Yes,
    @EnumValue("No")
    No,
}
