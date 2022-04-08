package dao_implimentation.session_logger;

import dao_implimentation.DbConnection;
import models.Logger;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 28/08/2017.
 */
public class LoggerDaoQuery {

    private Logger logger;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    public LoggerDaoQuery(){}

    @Inject
    private LoggerDaoQuery(DbConnection dbConnection){
        this.dbConnection = dbConnection;
        this.logger = new Logger();
        this.mongoCollection = dbConnection.getConnection("logger");
    }

    public boolean insert(Logger logger){
        if(logger != null){
            mongoCollection.insert(logger);
            checkResult = true;
        }
        return checkResult;
    }

    public Logger find(String query){
        return mongoCollection.findOne(query).as(Logger.class);
    }

    public List<Logger> findAllById(String query){
        List<Logger> list = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<Logger> loggers = mongoCollection.find(query).as(Logger.class);
            int count = loggers.count();
            if (count != 0) {
                for (Logger logger : loggers) {
                    list.add(logger);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return list;
    }

    public boolean upload(String query,Logger logger){
        if(query != null && logger != null){
            mongoCollection.update(query).with(logger);
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

}
