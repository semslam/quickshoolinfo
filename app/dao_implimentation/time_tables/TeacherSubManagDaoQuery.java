package dao_implimentation.time_tables;

import dao_implimentation.DbConnection;
import models.time_table.TeacherSubjectManagement;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 02/08/2017.
 */
public class TeacherSubManagDaoQuery {

    private DbConnection dbConnection;
    private TeacherSubjectManagement teacherSubManagt;
    private MongoCollection mongoCollection;
    private boolean checkResult = false;

    @Inject
    public TeacherSubManagDaoQuery(DbConnection  dbConnection) {
        this.dbConnection = dbConnection;
        this.teacherSubManagt = new TeacherSubjectManagement();
        this.mongoCollection = dbConnection.getConnection("teacher_subject_management");
    }

    public TeacherSubManagDaoQuery(){}

    public List<TeacherSubjectManagement> listAll(){
        List<TeacherSubjectManagement> teacherSubManags = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<TeacherSubjectManagement> teacherSubManagList = mongoCollection.find().as(TeacherSubjectManagement.class);
            int count = teacherSubManagList.count();
            if (count != 0) {
                for (TeacherSubjectManagement teacherSubManage : teacherSubManagList) {
                    teacherSubManags.add(teacherSubManage);
                }
            }
        }
        return teacherSubManags;
    }

    public List<TeacherSubjectManagement> listAllById(String query){
        List<TeacherSubjectManagement> teacherSubManags = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<TeacherSubjectManagement> teacherSubManagList = mongoCollection.find(query).as(TeacherSubjectManagement.class);
            int count = teacherSubManagList.count();
            if (count != 0) {
                for (TeacherSubjectManagement teacherSubManage : teacherSubManagList) {
                    teacherSubManags.add(teacherSubManage);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return teacherSubManags;
    }

    public TeacherSubjectManagement find(String query){
        return mongoCollection.findOne(query).as(TeacherSubjectManagement.class);
    }


    public boolean insert(TeacherSubjectManagement teacherSubManage){
        if(teacherSubManage != null){
            mongoCollection.insert(teacherSubManage);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean update(String query, TeacherSubjectManagement teacherSubManage){
        if(query != null && teacherSubManage != null){
            mongoCollection.update(query).with(teacherSubManage);
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

    public long count(String query){
        return mongoCollection.count(query);
    }

    public long count(){
        return mongoCollection.count();
    }

    public void aggregation(String query){
        mongoCollection.aggregate(query);
    }

}
