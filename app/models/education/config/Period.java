package models.education.config;

import models.MyModel;

import java.util.Date;
import  java.time.*;

/**
 * Created by Ibrahim Olanrewaju Abulsemiu on 07/03/2017.
 */
public class Period{
    public  long id;
    public String period;// 1st, 2nd, 3nd,
    public String periodType; // teaching, break
    public String isBreak; // yes, no
    public String startTime;
    public String endTime;
    public long modified;
    public Date modifierDate;
    public Date lastModified;
    public int counter;

    @Override
    public String toString() {
        return "Period{" +
                "id=" + id +
                ", period='" + period + '\'' +
                ", periodType='" + periodType + '\'' +
                ", isBreak='" + isBreak + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", modified=" + modified +
                ", modifierDate=" + modifierDate +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
