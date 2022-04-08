package dao_implimentation.fees;

import dao_implimentation.DbConnection;
import models.education.fees.StudentPayment;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRAVELDEN DEV on 11/07/2017.
 */
public class StdPaymentDoaQuery {

    private StudentPayment stdPayment;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult;
    @Inject
    public StdPaymentDoaQuery(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.stdPayment = new StudentPayment();
        this.mongoCollection = dbConnection.getConnection("student_payment");
    }

    public StudentPayment find(String query){return  mongoCollection.findOne(query).as(StudentPayment.class);}

    public List<StudentPayment> findAllById(String query){
        List<StudentPayment> studentPayments = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<StudentPayment> stdPaymentMongoCursor = mongoCollection.find(query).as(StudentPayment.class);
            int count = stdPaymentMongoCursor.count();
            if (count != 0) {
                for (StudentPayment studentPayment : stdPaymentMongoCursor) {
                    studentPayments.add(studentPayment);
                }
            }
        }
        return studentPayments;
    }

    public boolean insert(StudentPayment stdPayment){
        if(stdPayment != null){
            mongoCollection.insert(stdPayment);
            checkResult = true;
        }

        return checkResult;
    }

    public boolean upload(String query,StudentPayment stdPayment){
        if(query != null && stdPayment != null){
            mongoCollection.update(query).with(stdPayment);
            checkResult = true;
        }

        return checkResult;
    }

    public boolean delete(String query){
        mongoCollection.remove(query);
        return checkResult = true;
    }
}
