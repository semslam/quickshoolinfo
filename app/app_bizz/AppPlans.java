package app_bizz;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 28/08/2017.
 */
public class AppPlans {

    private static  final long DEMO_DAYS =  14;

    public static boolean demoValidate(long days){
        if(DEMO_DAYS != days){
          return true;
        }
        return false;
    }
}
