package controllers;

import data_services.FeatureView;
import models.application.Feature;
import play.data.Form;
import play.mvc.*;


import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 08/04/2017.
 */
public class FeatureCtr extends Controller {

    private FeatureView featureView;
    @Inject
    public FeatureCtr(FeatureView featureView){
        this.featureView = featureView;
    }


   /* public Result indexFeature(){

    }*/
    public Result insertFeature(){
        Form<Feature> featureForm = Form.form(Feature.class).bindFromRequest();
        Feature features = featureForm.get();
        boolean result;

        Form<Feature> form = Form.form(Feature.class).fill(features);
        if(featureForm.hasErrors()){
            flash().put("error","Invalid input field");
            return badRequest(views.html.authpage.features.add.render(form));
        }
        if (features.id <= 0){
            System.out.print(features.toString());
            result = featureView.insertFeature(features);
        }else{
            System.out.print(features.toString());
            result = featureView.updateFeature(features);
        }

        if(!result){
            flash().put("error","Invalid input field");
            return badRequest(views.html.authpage.features.add.render(form));
        }
        flash().put("success","Feature save is successful");
        return redirect(routes.FeatureCtr.findAllFeature());
    }

    public Result findFeatureById(long id){
        Feature feature = new Feature();
        if(id != 0){
            feature = featureView.findFeature(id);
        }
        Form<Feature> form = Form.form(Feature.class).fill(feature);
        return ok(views.html.authpage.features.add.render(form));
    }

    public Result findAllFeature(){
        List<Feature> featureList = featureView.findAllFeature();
        return ok(views.html.authpage.features.list.render(featureList));
    }

    public Result deleteFeature(long id){
        boolean result = true;
        if(id !=0){
            result = featureView.deleteFeature(id);
        }
        if (!result){
            flash().put("error","Feature Can't Delete From the fee_master");
            return redirect(routes.FeatureCtr.findAllFeature());
        }
        flash().put("success","The feature delete successfully");
        return redirect(routes.FeatureCtr.findAllFeature());
    }


}
