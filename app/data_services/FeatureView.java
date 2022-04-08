package data_services;

import api.auth.Auth;
import api.util.IDGenerator;
import dao_implimentation.FeatureDaoQuery;
import models.application.Feature;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 08/04/2017.
 */
public class FeatureView {
    private FeatureDaoQuery featureDaoQuery;
    private Feature features;
    private boolean checkFeature;
    @Inject
    public FeatureView(FeatureDaoQuery featureDaoQuery){
        this.features = new Feature();
        this.featureDaoQuery = featureDaoQuery;
    }
    public FeatureView(){}


    public boolean insertFeature(Feature feature){
        if(feature != null){
            feature.id = IDGenerator.subDocId();
            feature.modified = Auth.sessionUsersId();
            feature.modifiedDate = new Date();
            feature.lastModifiedDate = new Date();
            featureDaoQuery.insert(feature);
            checkFeature = true;
        }else{
            checkFeature = false;
        }
        return checkFeature;
    }

    public boolean updateFeature(Feature feature){
        if (feature != null){
            feature.modified = Auth.sessionUsersId();
            feature.lastModifiedDate = new Date();
            featureDaoQuery.update("{id:"+feature.id+"}",feature);
            checkFeature = true;
        }else{
            checkFeature = false;
        }
        return checkFeature;
    }

    public Feature findFeature(long id){
        return featureDaoQuery.find("{id:"+id+"}");
    }

    public List<Feature> findAllFeature(){
        List<Feature> featuresList = new ArrayList<>();
        try{
            featuresList = featureDaoQuery.findAll();
        }catch (Exception ioe){
            ioe.getMessage();
        }
        return featuresList;
    }

    public boolean deleteFeature(long id){
        if (!(id <= 0)){
          checkFeature =  featureDaoQuery.delete("{id:"+id+"}");
        }else{
            checkFeature = false;
        }
        return checkFeature;
    }
}
