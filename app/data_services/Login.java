package data_services;

import api.crypto.AESCrypt;
import javax.inject.Inject;
import dao_implimentation.UserDaoQuery;
import models.Users;



/**
 * Created by Ibrahim Olanrewaju S on 16/10/2016.
 **/
public class Login {
    private UserDaoQuery userDaoQuery;
    private Users users;
    public String user_email;
    public String password;


    @Inject
    public Login(UserDaoQuery userDaoQuery){
        this.userDaoQuery = userDaoQuery;
        this.users = new Users();
    }
    public Login(){}

    public Users authenticate(Login login){

        System.out.println("\n Email auth: "+ login.user_email);
        // latter in feature the app will get user school id via sub domain name to get there information on time

        Users users = userDaoQuery.find("{userEmail: '"+login.user_email+"'}");
       // Users users = userDaoQuery.findOne("{ user_email: { $exists: true, $nin: [ '"+ login.user_email+"' ] } }");
        if(users != null){
            System.out.println("\n Encrypt password: "+ users.password);
            try{password = AESCrypt.decrypt(users.password);}catch (Exception ex){ex.getMessage();}
            System.out.println("\n Decrypt password: "+ password);
            if(password.equals(login.password)){
                return users;
            }
        }
            return null;
    }

}
