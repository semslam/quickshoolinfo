package dao_implimentation.fees;

import dao_implimentation.DbConnection;
import models.education.fees.PaymentHistory;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Adulsemiu on 05/07/2017.
 */
public class PaymentHistoryDoaQuery {

    private PaymentHistory paymentHistory;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult;
    @Inject
    public PaymentHistoryDoaQuery(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.paymentHistory = new PaymentHistory();
        this.mongoCollection = dbConnection.getConnection("payment_history");
    }

    public PaymentHistory find(String query){return  mongoCollection.findOne(query).as(PaymentHistory.class);}

    public List<PaymentHistory> findAllById(String query){
        List<PaymentHistory> paymentHistories = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<PaymentHistory> paymentHistoryMongoCursor = mongoCollection.find(query).as(PaymentHistory.class);
            int count = paymentHistoryMongoCursor.count();
            if (count != 0) {
                for (PaymentHistory paymentHistory : paymentHistoryMongoCursor) {
                    paymentHistories.add(paymentHistory);
                }
            }
        }
        return paymentHistories;
    }

    public boolean insert(PaymentHistory paymentHistory){
        if(paymentHistory != null){
            mongoCollection.insert(paymentHistory);
            checkResult = true;
        }

        return checkResult;
    }

    public boolean upload(String query,PaymentHistory paymentHistory){
        if(query != null && paymentHistory != null){
            mongoCollection.update(query).with(paymentHistory);
            checkResult = true;
        }

        return checkResult;
    }

    public boolean delete(String query){
        mongoCollection.remove(query);
        return checkResult = true;
    }
}
