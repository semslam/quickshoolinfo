package dao_implimentation;

import models.std_records.Student;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer02 on 22/10/2016.
 */
public class StudentDoaQuery {
    private Student student;
    private DbConnection dbConnection;
    private MongoCollection mongoCollection;
    private boolean checkResult;
    @Inject
    public StudentDoaQuery(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.student = new Student();
        this.mongoCollection = dbConnection.getConnection("student");
    }

    public Student find(String query){return  mongoCollection.findOne(query).as(Student.class);}

    public List<Student> findAllById(String query){
        List<Student> students = new ArrayList<>();
        synchronized(mongoCollection) {
            MongoCursor<Student> studentMongoCursor = mongoCollection.find(query).as(Student.class);
            int count = studentMongoCursor.count();
            if (count != 0) {
                for (Student student : studentMongoCursor) {
                    students.add(student);
                }
            }
        }
        return students;
    }

    public boolean insert(Student std){
        if(std != null){
            mongoCollection.insert(std);
            checkResult = true;
        }

       return checkResult;
    }

    public boolean upload(String query,Student std){
        if(query != null && std != null){
            mongoCollection.update(query).with(std);
            checkResult = true;
        }

        return checkResult;
    }

    public boolean delete(String query){
        mongoCollection.remove(query);
       return checkResult = true;
    }
}
