package controllers.guardian;

import data_services.actors_record.guardian.GuardianRecord;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;

/**
 * Created by Ibrahim Olanrewaju Semiu on 03/03/2017.
 */
public class GuardianCtr extends Controller {

    private GuardianRecord guardianRecord;

    @Inject
    private GuardianCtr(GuardianRecord guardianRecord){
        this.guardianRecord = guardianRecord;
    }

    public Result guardianForm(){

        return TODO;
    }

    public Result addNewGuardian(){
        return TODO;
    }

    public Result getGuardianById(long id){
        return TODO;
    }

    public Result updateGuardian(){
        return  TODO;
    }

    public Result deleteGuardian(long id){
        return TODO;
    }
}
