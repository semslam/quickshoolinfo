package dao_implimentation;

import api.auth.Auth;
import com.mongodb.WriteResult;
import models.education.config.ClassRoom;
import models.education.config.SchoolConfiguration;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Ibrahim Olanrewaju S on 15/01/2017.
 */
public class SchConfigDaoQuery {
    private SchoolConfiguration schoolConfiguration;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;
    @Inject
    public SchConfigDaoQuery(DbConnection dbConnection){
        this.dbConnection = dbConnection;
        this.schoolConfiguration = new SchoolConfiguration();
        this.mongoCollection = dbConnection.getConnection("schoolConfig");
    }
    public SchConfigDaoQuery(){}

    public boolean insert(SchoolConfiguration obj){
        try{
            out.println(mongoCollection.toString());
            final WriteResult insert = mongoCollection.insert(obj);
            out.println(insert);
            checkResult = true;
        }catch (Exception e){
            checkResult = false;
        }
        return checkResult;
    }

    public SchoolConfiguration findOne(String query){
        out.println("\n\n"+query);
        return mongoCollection.findOne(query).as(SchoolConfiguration.class);
    }

    public boolean uploadSubDocument(String subDocId,String subDocName,Object schConf){
        try{
            mongoCollection.update("{_id: '"+Auth.sessionSchoolId()+"', "+subDocId+"}").with("{$set: {"+subDocName+".$ : #}}",schConf);
            checkResult = true;
        }catch (Exception e){
            checkResult = false;
        }

        return checkResult;
    }

    public boolean addNewSubDoc(String subDocName, Object obj) {
            try{
                mongoCollection.update("{_id: '"+ Auth.sessionSchoolId()+"'}").with("{$push: {"+subDocName+": #}}", obj);
                return checkResult = true;
            }catch (Exception e){
                checkResult = false;
            }
        return  checkResult;
    }

    public void delete(String query){
        mongoCollection.remove(query);
    }

    public SchoolConfiguration findAllById(String id){
        SchoolConfiguration schConf = new SchoolConfiguration();
        List<SchoolConfiguration> schConfigList = new ArrayList<>();
        try{
            MongoCursor<SchoolConfiguration> schoolConfigurationList = mongoCollection.find("{_id: '"+id+"'}").as(SchoolConfiguration.class);
            if(schoolConfigurationList.count() == 0){
                out.println("\n\n");
                out.println("Yes Zero");
            }
            for(SchoolConfiguration scf: schoolConfigurationList){
                    if (scf._id.equals(id))
                        schConf = scf;
                        break;
            }
            schoolConfigurationList.close();
        }catch (IOException ioe){
            ioe.getMessage();
        }
        return schConf;
    }


}
