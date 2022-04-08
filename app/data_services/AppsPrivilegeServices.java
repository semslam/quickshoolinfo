package data_services;

import dao_implimentation.AppsPrivilegeDaoQuery;
import models.privileges.ApplicationPrivilege;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRAVELDEN DEV on 17/04/2017.
 */
public class AppsPrivilegeServices {

    private ApplicationPrivilege appsPrivilege;
    private AppsPrivilegeDaoQuery appsDaoQuery;
    private boolean checkResult;

    @Inject
    public AppsPrivilegeServices(AppsPrivilegeDaoQuery appsDaoQuery){
        this.appsDaoQuery = appsDaoQuery;
        this.appsPrivilege = new ApplicationPrivilege();
    }
    public  AppsPrivilegeServices(){}




    public boolean insertAppsPrivilege(ApplicationPrivilege appPrivilege){
        if(appPrivilege != null){
            appsDaoQuery.insert(appsPrivilege);
            checkResult = true;
        }else{
            checkResult = false;
        }
        return checkResult;
    }

    public boolean updateAppsPrivilege(ApplicationPrivilege appPrivilege){
        if (appPrivilege != null){
            appsDaoQuery.update("{id:"+appPrivilege.id+"}",appPrivilege);
            checkResult = true;
        }else{
            checkResult = false;
        }
        return checkResult;
    }

    public ApplicationPrivilege findAppsPrivilege(long id){
        return appsDaoQuery.find("{id:"+id+"}");
    }

    public List<ApplicationPrivilege> findAllAppsPrivilege(){
        List<ApplicationPrivilege> appPrivileges = new ArrayList<>();
        try{
            appPrivileges = appsDaoQuery.findAll();
        }catch (Exception ioe){
            ioe.getMessage();
        }
        return appPrivileges;
    }

    public boolean deleteAppsPrivilege(long id){
        if (!(id <= 0)){
            checkResult =  appsDaoQuery.delete("{id:"+id+"}");
        }else{
            checkResult = false;
        }
        return checkResult;
    }
}
