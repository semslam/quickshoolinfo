package dao_implimentation.time_tables;

import dao_implimentation.DbConnection;
import models.time_table.SubjectOfTheDay;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 02/08/2017.
 */
public class SubjectOfTheDayDaoQuery {

    private DbConnection dbConnection;
    private SubjectOfTheDay subjectOfTheDay;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    @Inject
    public SubjectOfTheDayDaoQuery(DbConnection  dbConnection) {
        this.dbConnection = dbConnection;
        this.subjectOfTheDay = new SubjectOfTheDay();
        this.mongoCollection = dbConnection.getConnection("subjectOfTheDay");
    }

    public SubjectOfTheDayDaoQuery(){}

    public List<SubjectOfTheDay> listAll(){
        List<SubjectOfTheDay> subOfTheDays = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<SubjectOfTheDay> subOfTheDayList = mongoCollection.find().as(SubjectOfTheDay.class);
            int count = subOfTheDayList.count();
            if (count != 0) {
                for (SubjectOfTheDay subjectOfTheDay : subOfTheDayList) {
                    subOfTheDays.add(subjectOfTheDay);
                }
            }
        }
        return subOfTheDays;
    }

    public List<SubjectOfTheDay> listAllById(String query){
        List<SubjectOfTheDay> subjectOfTheDays = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<SubjectOfTheDay> subjectOfTheDaysList = mongoCollection.find(query).as(SubjectOfTheDay.class);
            int count = subjectOfTheDaysList.count();
            if (count != 0) {
                for (SubjectOfTheDay subjectOfTheDay : subjectOfTheDaysList) {
                    subjectOfTheDays.add(subjectOfTheDay);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return subjectOfTheDays;
    }

    public SubjectOfTheDay find(String query){
        return mongoCollection.findOne(query).as(SubjectOfTheDay.class);
    }


    public boolean insert(SubjectOfTheDay subjectOfTheDay){
        if(subjectOfTheDay != null){
            mongoCollection.insert(subjectOfTheDay);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean update(String query, SubjectOfTheDay subjectOfTheDay){
        if(query != null && subjectOfTheDay != null){
            mongoCollection.update(query).with(subjectOfTheDay);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean delete(String query){
        if(query != null){
            mongoCollection.remove(query);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean insertMany(){

        return true;
    }

    public long count(String query){
        return mongoCollection.count(query);
    }

    public long count(){
        return mongoCollection.count();
    }

    public void aggregation(String query){
        mongoCollection.aggregate(query);
    }

}
