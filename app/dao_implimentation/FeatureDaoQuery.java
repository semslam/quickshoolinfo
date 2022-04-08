package dao_implimentation;

import com.mongodb.gridfs.GridFS;
import models.application.Feature;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRAVELDEN DEV on 08/04/2017.
 */
public class FeatureDaoQuery {
    private Feature feature;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private static final Logger logger = LoggerFactory.getLogger("logger");

    private GridFS mongoGridfs;
    private boolean checkFeature = false;
    @Inject
    public FeatureDaoQuery(DbConnection dbConnection){
        this.dbConnection = dbConnection;
        this.feature = new Feature();
        mongoGridfs = dbConnection.getGridBinary();
        mongoCollection = dbConnection.getConnection("feature");
    }

    public boolean insert(Feature feature){

        if(feature != null) {
            mongoCollection.insert(feature);
            mongoGridfs.createFile();
            checkFeature = true;
        }else{
            checkFeature = false;
            logger.error("Failed", "Failed to insert into feature collection");
        }
        return checkFeature;
    }

    public boolean update(String query,Feature feature){
        if(feature != null && query != null) {
            mongoCollection.update(query).with(feature);
            checkFeature = true;
        }else{
            checkFeature = false;
        }
        return checkFeature;
    }

    public Feature find(String query){
        return mongoCollection.findOne(query).as(Feature.class);
    }

    public List<Feature> findAll()throws IOException{
        List<Feature> features = new ArrayList<>();
        MongoCursor<Feature> featuresList = mongoCollection.find().as(Feature.class);
        if(featuresList.count() != 0 && featuresList != null){
            for(Feature f: featuresList){
                features.add(f);
            }
            featuresList.close();
        }
        return features;
    }

    public boolean delete(String query){
        if(query != null) {
            mongoCollection.remove(query);
            checkFeature = true;
        }else{
            checkFeature = false;
        }
        return checkFeature;

    }

}