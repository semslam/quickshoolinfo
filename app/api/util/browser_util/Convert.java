package api.util.browser_util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by TRAVELDEN DEV on 20/04/2017.
 */
public class Convert<E> {

    public E clazz;

    public Convert(E clazz){
        this.clazz = clazz;
    }

   /* public List<E> toList(){
        List<E> es = new ArrayList<>();
        for(E e: clazz){
            es.add(e);
        }
        return es;
    }*/
}
