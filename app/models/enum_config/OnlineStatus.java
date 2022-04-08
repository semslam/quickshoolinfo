package models.enum_config;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 29/08/2017.
 */
public enum OnlineStatus {

    Online("online"),Offline("offline"),Away("away"),DoNotDisturb("doNotDisturb"),Invisible("invisible");
    String s;
    OnlineStatus(String s){this.s = s;}
    public String getValue(){ return  s;}
}
