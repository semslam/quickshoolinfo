package dao_implimentation;

import models.guardian_record.Guardian;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer02 on 21/10/2016.
 */
public class GuardianDoaQuery {

    private Guardian guardian;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    @Inject
    public GuardianDoaQuery(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.guardian = new Guardian();
        this.mongoCollection = dbConnection.getConnection("guardian");
    }

    public Guardian find(String query){
        return  mongoCollection.findOne(query).as(Guardian.class);
    }

    public boolean insert(Guardian guardian){
        if(guardian != null){
            synchronized(mongoCollection) {
                mongoCollection.insert(guardian);
                checkResult = true;
            }
        }
        return checkResult;
    }

    public List<Guardian> findAllById(String query){
        List<Guardian> list = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<Guardian> guardiens = mongoCollection.find(query).as(Guardian.class);
            int count = guardiens.count();
            if (count != 0) {
                for (Guardian guardian : guardiens) {
                    list.add(guardian);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return list;
    }

    public void upload(String query, Guardian guardian){
        mongoCollection.update(query).with(guardian);
    }

    public void delete(String query){
        mongoCollection.remove(query);
    }




    //public List[Guardian] listAll(){ return null;}

}
