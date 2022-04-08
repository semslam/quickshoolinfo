package data_services.sch_config;

import api.auth.Auth;
import dao_implimentation.SchoolDaoQuery;
import models.education.School;
import models.privileges.SchPrivilege;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S DEV on 17/04/2017.
 */
public class SchPrivilegeServices {

    private School school;
    private SchoolDaoQuery schoolDaoQuery;
    private boolean checkResult;
    @Inject
    private SchPrivilegeServices(SchoolDaoQuery schoolDaoQuery){
        this.school = new School();
        this.schoolDaoQuery = schoolDaoQuery;
    }

    public SchPrivilegeServices(){}

    public boolean updateSchPrivilege(SchPrivilege schPrivilege){
        School school = new School();
        String query = "{_id:"+ Auth.sessionSchoolId()+",schPrivileges.id: 484947943 ,schPrivileges.subModuleCruds.id: 57485574}";
        if (schPrivilege != null){
            school.schPrivileges = Arrays.asList(schPrivilege);

           schoolDaoQuery.update(query,school);
            checkResult = true;
        }else{
            checkResult = true;
        }
        return checkResult;
    }

    public School findSchPrivilegeById(String query){
       return schoolDaoQuery.find(query);
    }

    public List<SchPrivilege> findAllSchPrivilege(){
        String query = "{_id: '"+Auth.sessionSchoolId()+"'},{ schPrivileges: 1}";
        List<SchPrivilege> schPrivileges = new ArrayList<>();
        try{
            schPrivileges = schoolDaoQuery.find(query).schPrivileges;
        }catch (Exception ioe){
            ioe.getMessage();
        }
        return schPrivileges;
    }

    public School findClientPrivilege(String id){
        String query = "{_id: '"+id+"'},{ schPrivileges: 1}";

        try{
            school = schoolDaoQuery.find(query);
        }catch (Exception ioe){
            ioe.getMessage();
        }
        return school;
    }
}
