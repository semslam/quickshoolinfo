package dao_implimentation;

import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S on 29/12/2016.
 */
public interface DaoModelsInter {

    void insert();

    void delete(String id);

    void  delete(long id);

    void getId(String id);

    void getId(long id);

    Object findById(long id);

    Object findById(String id);

    List<Object> findAll();

}
