/*
package dao_implimentation;

import com.mongodb.MongoClientException;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by Ibrahim Olanrewaju S on 14/07/2016.
 *//*

public abstract class Models<T> implements Serializable {
    private T t;

    private Class<T> modelsClass;

    public abstract String tableName();

    protected MongoCollection collections;// = DbConnection.getDbConnection().getCollection(tableName());

    public MongoCollection getDbCollection(){
        return this.collections;
    }

    public List<T> listAll(){
        List<T> lists = new ArrayList<>();
         MongoCursor<T> findAll = collections.find().as(modelsClass);
        int nbResults = findAll.count();
        // check if size is not to zero
        if(nbResults != 0){
            for (T t: findAll) {
                lists.add(t);
            }
        }

        return lists;
    }

    //Friend one = friends.findOne("{name: 'Joe'}").as(Friend.class);
    public T findById(String query) {
        try{
            t =  (T)collections.find(query);
            return t;
        }catch (MongoClientException me){
            me.fillInStackTrace();
        return  null;
        }
    }
    // check if the field exist, if it exist return true else return false
    public boolean findByField(String field, String query){
        try{
            t =  (T)collections.findOne("{"+field+": '"+query+"'}");
            if(t.equals(field)== true){}
            return true;
        }catch (MongoClientException me){
            me.fillInStackTrace();
            return  false;
        }
    }

    public boolean fee_master(T t){
        try{ collections.fee_master(t);return true;}catch (MongoClientException me){me.fillInStackTrace(); return  false;}
    }
    public boolean update(T t){
        try{ collections.save(t);return true;}catch (MongoClientException me){me.fillInStackTrace(); return  false;}
    }
    public boolean disable(T t){
        return true;
    }
   */
/* public boolean delete(T t){
        try{ collections.remove(t);return true;}catch (MongoClientException me){me.fillInStackTrace(); return  false;}
    }*//*


}
*/
