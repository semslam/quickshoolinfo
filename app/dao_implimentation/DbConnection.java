package dao_implimentation;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import play.Configuration;
import play.Logger;

import javax.inject.Inject;
import java.net.UnknownHostException;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 6/29/2016.
 */
public class DbConnection {

    private static volatile DbConnection service = null;
    private Mongo mongo;
    protected Configuration configuration;
    protected String connectionURIs;
    protected String dbName;
    protected Jongo jongo;
    protected GridFS gridfs;


    @Inject
    public DbConnection(Configuration configuration, Logger logger){
        this.configuration = configuration;
        connectionURIs = configuration.getString("mongodb.db.url");
        dbName = configuration.getString("mongodb.db.database");
        MongoClient client = new MongoClient(new MongoClientURI(connectionURIs));
        this.jongo = new Jongo(client.getDB(dbName));

        if (Boolean.parseBoolean(configuration.getString("mongo.db.gridfs.enabled"))) {
            this.gridfs = new GridFS(jongo.getDatabase(), "binary");
        }
    }
    public DbConnection(){}


    /*public static void init() {

        synchronized (DbConnection.class) {
            if (service == null) {
                try {
                    service = new DbConnection();
                } catch (MongoException e) {
                    Logger.error("MongoException", e);
                }
            }
        }
    }*/

    public static void shutdown() {
        service.mongo.close();
    }

    public static Jongo jongo() {
        return service.jongo;
    }


     public MongoCollection getConnection(String collectionName){
        return jongo.getCollection(collectionName);
    }
  /*  public MongoCollection getConnection(String collectionName){
        return service.jongo.getCollection(collectionName);
    }*/
    public GridFS getGridBinary(){
        return gridfs;
    }

    public GridFS getImageConnection(String imageCollection){
        return gridfs = new GridFS(gridfs.getDB(),imageCollection);
    }

    // mongo increment { $inc: { quantity: -2, "metrics.orders": 1 } }
}