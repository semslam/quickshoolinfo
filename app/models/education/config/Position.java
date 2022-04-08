package models.education.config;

import models.MyModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Adbulemiu on 28/03/2017.
 */
public class Position extends  MyModel{
    public long id;
    public String name;
    public String shortName;
    public String type; // student staff guadiance
    public List<String> roles;


    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", roles=" + Arrays.asList(roles) +
                ", modifier=" + modifier +
                ", modified=" + modified +
                ", lastModified=" + lastModified +
                '}';
    }
}
