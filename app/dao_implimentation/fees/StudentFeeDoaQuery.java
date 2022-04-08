package dao_implimentation.fees;

import dao_implimentation.DbConnection;
import models.education.fees.StudentFee;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Adulsemiu on 05/07/2017.
 */
public class StudentFeeDoaQuery {

    private StudentFee studentFee;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult;
    @Inject
    public StudentFeeDoaQuery(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.studentFee = new StudentFee();
        this.mongoCollection = dbConnection.getConnection("student_fee");
    }

    public StudentFee find(String query){return  mongoCollection.findOne(query).as(StudentFee.class);}

    public List<StudentFee> findAllById(String query){
        List<StudentFee> studentFees = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<StudentFee> studentFeeMongoCursor = mongoCollection.find(query).as(StudentFee.class);
            int count = studentFeeMongoCursor.count();
            if (count != 0) {
                for (StudentFee studentFee : studentFeeMongoCursor) {
                    studentFees.add(studentFee);
                }
            }
        }
        return studentFees;
    }

    public boolean insert(StudentFee studentFee){
        if(studentFee != null){
            mongoCollection.insert(studentFee);
            checkResult = true;
        }

        return checkResult;
    }

    public boolean upload(String query,StudentFee studentFee){
        if(query != null && studentFee != null){
            mongoCollection.update(query).with(studentFee);
            checkResult = true;
        }

        return checkResult;
    }

    public boolean delete(String query){
        mongoCollection.remove(query);
        return checkResult = true;
    }
}
