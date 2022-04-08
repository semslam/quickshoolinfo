package dao_implimentation;

import com.mongodb.gridfs.GridFS;
import models.education.config.Image;

import java.io.File;
import java.io.InputStream;

/**
 * Created by TRAVELDEN DEV on 04/03/2017.
 */
public class UsersImageDaoQuery {

    private DbConnection dbConnection;
    private GridFS gridFS;
    private Image image;
    private  boolean checkResult;

    private UsersImageDaoQuery(DbConnection dbConnection){
        this.dbConnection = dbConnection;
        this.image = new Image();
        this.gridFS = dbConnection.getImageConnection("usersImage");
    }


    public boolean insert(InputStream in, long id){
        if(in != null && image != null){
           gridFS.createFile(in,Long.toString(id));
           checkResult = true;
        }else{
            checkResult = false;
        }
       return checkResult;
    }
}
