package data_services;

import api.auth.Auth;
import dao_implimentation.SchoolDaoQuery;
import models.education.School;

import javax.inject.Inject;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 07/08/2017.
 */
public class SchoolServices {

    public SchoolDaoQuery schoolDaoQuery;
    public School school;
    public boolean checkResult = false;

    @Inject
    public SchoolServices(SchoolDaoQuery schoolDaoQuery){
        this.schoolDaoQuery = schoolDaoQuery;
        this.school = new School();
    }

    public SchoolServices(){}


    public boolean update(School sch){
        if(sch == null)
        schoolDaoQuery.update("{_id: '"+Auth.sessionSchoolId()+"'}",sch);
        return checkResult = true;
    }

    public School find(){
        return  school = schoolDaoQuery.find("{_id:'"+ Auth.sessionSchoolId()+"'}");
    }

    public School findById(String id){
        if(!id.isEmpty())
           school = schoolDaoQuery.find(id);
        return school;
    }

}
