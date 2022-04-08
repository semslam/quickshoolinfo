package models.education.config;

import models.MyModel;
import org.jongo.marshall.jackson.oid.Id;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 30/08/2017.
 */
public class ClassRoomType extends MyModel{
    @Id
    public long id;
    public String name;
    public String abbreviation;
}
