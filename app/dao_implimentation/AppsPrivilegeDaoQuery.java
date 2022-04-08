package dao_implimentation;

import models.privileges.ApplicationPrivilege;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 22/03/2017.
 */
public class AppsPrivilegeDaoQuery {
    private ApplicationPrivilege appPrivilege;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;
    @Inject
    public AppsPrivilegeDaoQuery(DbConnection dbConnection){
        this.dbConnection = dbConnection;
        this.appPrivilege = new ApplicationPrivilege();
        this.mongoCollection = dbConnection.getConnection("appsPrivilege");
    }


    public boolean insert(ApplicationPrivilege appPrivilege){
        if(appPrivilege != null) {
            mongoCollection.insert(appPrivilege);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean update(String query,ApplicationPrivilege appPrivilege){
        if(appPrivilege != null) {
            mongoCollection.update(query).with(ApplicationPrivilege.class);
            checkResult = true;
        }
        return checkResult;
    }

    public ApplicationPrivilege find(String query){
        return mongoCollection.findOne(query).as(ApplicationPrivilege.class);
    }


    public List<ApplicationPrivilege> findAll()throws IOException {
        List<ApplicationPrivilege> appsPrivileges = new ArrayList<>();
        MongoCursor<ApplicationPrivilege> appsPrivilegeList = mongoCollection.find().as(ApplicationPrivilege.class);
        if(appsPrivilegeList.count() != 0 && appsPrivilegeList != null){
            for(ApplicationPrivilege apps: appsPrivilegeList){
                appsPrivileges.add(apps);
            }
            appsPrivilegeList.close();
        }
        return appsPrivileges;
    }

    public boolean delete(String query){
        if(query != null) {
            mongoCollection.remove(query);
            checkResult = true;
        }else{
            checkResult = false;
        }
        return checkResult;

    }


}
