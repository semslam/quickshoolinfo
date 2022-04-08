package dao_implimentation.time_tables;

import dao_implimentation.DbConnection;
import models.time_table.SubjectOfDPeriodDetail;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 02/08/2017.
 */
public class SubjOfThePeriodDetailDaoQuery {

    private DbConnection dbConnection;
    private SubjectOfDPeriodDetail subOfDPeriodDetail;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    @Inject
    public SubjOfThePeriodDetailDaoQuery(DbConnection  dbConnection) {
        this.dbConnection = dbConnection;
        this.subOfDPeriodDetail = new SubjectOfDPeriodDetail();
        this.mongoCollection = dbConnection.getConnection("subject_period_detail");
    }

    public SubjOfThePeriodDetailDaoQuery(){}

    public List<SubjectOfDPeriodDetail> listAll(){
        List<SubjectOfDPeriodDetail> subOfDPeriodDetails = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<SubjectOfDPeriodDetail> subOfDPeriodDetailList = mongoCollection.find().as(SubjectOfDPeriodDetail.class);
            int count = subOfDPeriodDetailList.count();
            if (count != 0) {
                for (SubjectOfDPeriodDetail subOfDPeriodDetail : subOfDPeriodDetailList) {
                    subOfDPeriodDetails.add(subOfDPeriodDetail);
                }
            }
        }
        return subOfDPeriodDetails;
    }

    public List<SubjectOfDPeriodDetail> listAllById(String query){
        List<SubjectOfDPeriodDetail> subOfDPeriodDetails = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<SubjectOfDPeriodDetail> subOfDPeriodDetailList = mongoCollection.find(query).as(SubjectOfDPeriodDetail.class);
            int count = subOfDPeriodDetailList.count();
            if (count != 0) {
                for (SubjectOfDPeriodDetail subOfDPeriodDetail : subOfDPeriodDetailList) {
                    subOfDPeriodDetails.add(subOfDPeriodDetail);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return subOfDPeriodDetails;
    }

    public SubjectOfDPeriodDetail find(String query){
        return mongoCollection.findOne(query).as(SubjectOfDPeriodDetail.class);
    }


    public boolean insert(SubjectOfDPeriodDetail subOfDPeriodDetail){
        if(subOfDPeriodDetail != null){
            mongoCollection.insert(subOfDPeriodDetail);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean update(String query, SubjectOfDPeriodDetail subOfDPeriodDetail){
        if(query != null && subOfDPeriodDetail != null){
            mongoCollection.update(query).with(subOfDPeriodDetail);
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
