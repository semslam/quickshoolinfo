package dao_implimentation;

import models.education.School;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer02 on 07/09/2016.
 */
public class SchoolDaoQuery {


    private School school;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    public boolean checkResult = false;

    public SchoolDaoQuery() {
    }

    @Inject
    private SchoolDaoQuery(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.school = new School();
        this.mongoCollection = dbConnection.getConnection("school");
    }

    public boolean insert(School school) {
        if (school != null) {
            mongoCollection.insert(school);
            checkResult = true;
        }
        return checkResult;
    }

    public School find(String query) {
        return mongoCollection.findOne(query).as(School.class);
    }

    public boolean update(String query, School school) {
        synchronized (mongoCollection) {
            if (query != null && school != null) {
                mongoCollection.update(query).with(school);
                checkResult = true;
            }
        }
        return checkResult;
    }

    public boolean delete(String query) {
        if (query != null) {
            mongoCollection.remove(query);
            checkResult = true;
        }
        return checkResult;
    }

    public List<School> findAll() throws IOException {
        List<School> schoolList = new ArrayList<>();
        MongoCursor<School> schools = mongoCollection.find().as(School.class);
        if (schools.count() != 0 && schools != null) {
            for (School s : schools) {
                schoolList.add(s);
            }
            schools.close();
        }
        return schoolList;
    }

}
