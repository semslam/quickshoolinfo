package models.enum_config;


//import com.avaje.ebean.annotation.EnumValue;

/**
 * Created by Ibrahim Olanrewaju S  on 28/12/2016.
 */
public enum Status {

    Pending("Pending"),InActivate("InActive"),Activate("Active"),Deleted("Delete"),Demo("Demo");
    String s;
     Status(String s){this.s = s;}
     public String getValue(){ return  s;}

}
