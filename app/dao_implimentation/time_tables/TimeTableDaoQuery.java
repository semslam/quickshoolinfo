package dao_implimentation.time_tables;

import dao_implimentation.DbConnection;
import models.Users;
import models.time_table.TimeTable;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 02/08/2017.
 */
public class TimeTableDaoQuery {

    private DbConnection dbConnection;
    private TimeTable timeTable;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    @Inject
    public TimeTableDaoQuery(DbConnection  dbConnection) {
        this.dbConnection = dbConnection;
        this.timeTable = new TimeTable();
        this.mongoCollection = dbConnection.getConnection("time_table");
    }

    public TimeTableDaoQuery(){}

    public List<TimeTable> listAll(){
        List<TimeTable> timeTables = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<TimeTable> timeTablesList = mongoCollection.find().as(TimeTable.class);
            int count = timeTablesList.count();
            if (count != 0) {
                for (TimeTable timeTable : timeTablesList) {
                    timeTables.add(timeTable);
                }
            }
        }
        return timeTables;
    }

    public List<TimeTable> listAllById(String query){
        List<TimeTable> timeTableList = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<TimeTable> timeTables = mongoCollection.find(query).as(TimeTable.class);
            int count = timeTables.count();
            if (count != 0) {
                for (TimeTable timeTable : timeTables) {
                    timeTableList.add(timeTable);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return timeTableList;
    }

    public TimeTable find(String query){
        return mongoCollection.findOne(query).as(TimeTable.class);
    }


    public boolean insert(TimeTable timeTable){
        if(timeTable != null){
            mongoCollection.insert(timeTable);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean update(String query,Users users){
        if(query != null && users != null){
            mongoCollection.update(query).with(users);
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
