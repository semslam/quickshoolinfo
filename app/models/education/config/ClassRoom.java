package models.education.config;

import models.MyModel;

import java.util.Date;
import java.util.List;

public class ClassRoom{
	// school class room
	public long id;
	public long classGrade;// class name e.g grade5, class5, primary5 // optional
	public String className; // abbreviation name giving to class 2A,2B,2C
	public long classDepartment;
	public String floor; // the class room floor // optional
	public long classType;// lab or class room
	public String building;// the name of the building the class is
	public String capacity;// class capacity e.g 11 by 12
	public String numberOfSite;// number of site in the  class
	public String stdCapacity;// student capacity in the class
	public long modifier;
	public Date modified;
	public Date lastModified;
	public int counter;

	@Override
	public String toString() {
		return "ClassRoom{" +
				"id=" + id +
				", classGrade='" + classGrade + '\'' +
				", className='" + className + '\'' +
				", classDepartment='" + classDepartment + '\'' +
				", floor='" + floor + '\'' +
				", classType='" + classType + '\'' +
				", building='" + building + '\'' +
				", capacity='" + capacity + '\'' +
				", numberOfSite='" + numberOfSite + '\'' +
				", stdCapacity='" + stdCapacity + '\'' +
				", modifier=" + modifier +
				", modified=" + modified +
				", lastModified=" + lastModified +
				", counter=" + counter +
				'}';
	}
}
