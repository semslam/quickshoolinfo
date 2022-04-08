package api.util;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Ibrahim Olanrewaju S on 02/09/2016.
 */
public class IDGenerator {

    private static Random generator;
    private static final  String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static Long longRandomId(){
        return (long) (Math.random() * 2 * Long.MAX_VALUE - Long.MAX_VALUE);
    }

    public static String stringUUID(){
      UUID uniqueKey = UUID.randomUUID();String id = String.valueOf(uniqueKey);return id;
    }
    // users id
    public static long userIdGenerator(){
        Date dt = new Date();
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyMMddHHmmss");
        String generate = "";
        String random;
        generator = new Random();
        random = String.valueOf(generator.nextInt(100000));
        int Length = random.length()+ sdf.format(dt).length();
        while (Length < 18) {
            generate = "0" + random + sdf.format(dt);
            Length += 1;
        }
        long gen = Long.parseLong(generate);
        return gen;
    }

    public static String tokenCode(){
        char[] chars = ALPHABET.toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String gen = String.valueOf(random.nextInt(1000));
        String output = gen + sb.toString();
        List<Character> characters = new ArrayList<>();
        for (char cs : output.toCharArray()) {
            characters.add(cs);
        }
        StringBuilder outputs = new StringBuilder(output.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            outputs.append(characters.remove(randPicker));
        }
        return outputs.toString();
    }

    // collections id
    public static long subDocId() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Long.parseLong(str);
    }

    /*public static long subDocId(){
        Date dt = new Date();
        SimpleDateFormat sdf =
                new SimpleDateFormat("MMddHHmmssSSS");
         long id = Long.parseLong(sdf.format(dt));
        return id;
    }*/

    public static String StdAdmissionNo(String gen ){
        return gen;
    }

    public static String adminConfirmationCode(){
        return "";
    }

    public static String PaymentReference(String payCode){
        return payCode;
    }

  /*  public static void main(String []args){
       long gen = IDGenerator.subDocId();

        System.out.println(gen);
    }*/

}
