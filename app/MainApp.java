import api.util.DateFormat;
import api.crypto.AESCrypt;
import data_services.signup.AdminSignUp;

import java.util.UUID;

/**
 * Created by TRAVELDEN DEV on 17/12/2016.
 */
public class MainApp {

    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    public static String RegistrationId (String value){
        // pick out three letters out of school name and add unique number on it with ST if it is staff or SD if it is student
        // and it as a string
        return "";
    }

    // XXX: replace with java.util.UUID

    public String getTime(){
        String value = "23:59";
        String[] time = value.split(":");
        String hour = time[0];
        String minute = time[1];
        int get;
        String amORpm ="";
        int hours = Integer.parseInt(splitTime(hour));
        if (hours == 00){
            get = 12;
            amORpm = "AM";
        }else if(hours > 00 || hours <= 11){
            get = hours;
            amORpm = "AM";
        }else if(hours >= 12 || hours <= 23){
            get = hours > 12 ? hours - 12 : 01;
            amORpm = "PM";
        }
        return amORpm;
    }

    private String splitTime(String value){
        String st;
        int int_val = Integer.parseInt(value.substring(0,value.length()));
        if(int_val > 0){
            st = value;
        }else{
            st = Integer.toString(Integer.parseInt(value.substring(1,value.length())));
        }
         return st;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(generateUniqueId());
            //generateUniqueId();
        }

       // System.out.println(DateFormat.getTimeFormat("08:05"));
        try{

          System.out.println("Encrypt Password: "+AESCrypt.encrypt("showme007"));

        }catch (Exception e){
            e.getMessage();
        }

    }
   /* public static void main(String[] args)throws Exception{
        AdminSignUp adminSignUp = new AdminSignUp();
        AdminSignUp adminSignUp2 = new AdminSignUp();
        adminSignUp2.subDomain = "brown";
        adminSignUp2.email = "islam@hot.com";
        //String result = adminSignUp.isUserActive(adminSignUp2);
       // System.out.println("Show me what u got:: "+result);

    }*/
}
