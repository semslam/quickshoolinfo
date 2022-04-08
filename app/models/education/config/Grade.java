package models.education.config;

import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju Semiu on 05/03/2017.
 */
public class Grade {

    public Grade(long id, String name, String gradeCode, int fromAge, int toAge, String country, long modifier, Date modified, Date lastModified){
        this.id = id;
        this.name = name; this.gradeCode = gradeCode; this.fromAge = fromAge; this.toAge = toAge; this.country = country;
        this.modifier = modifier; this.modified = modified; this.lastModified = lastModified;
    }
    public Grade(){}

    public long id;
    public String name;
    public String gradeCode;
    public int fromAge;
    public int toAge;
    public String country;
    public long modifier;
    public Date modified;
    public Date lastModified;
    public int counter;

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gradeCode='" + gradeCode + '\'' +
                ", fromAge=" + fromAge +
                ", toAge=" + toAge +
                ", country='" + country + '\'' +
                ", modifier=" + modifier +
                ", modified=" + modified +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
