package models.education.config;

import models.MyModel;

import java.util.Date;

public class Subject{
	public long id;
	public String subjectId;
	public String subShortName;
	public String subjectName;
	public long department;
	public long grade; // which subject grade
	public int minMark;
	public int maxMark;
	public String descr;
	public String compulsory;// yes or no
	public String examCompulsory;// yes or no
	public String subjectColour;
	public long modified;
	public Date modifierDate;
	public Date lastModified;

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", subjectId='" + subjectId + '\'' +
				", subShortName='" + subShortName + '\'' +
				", subjectName='" + subjectName + '\'' +
				", department='" + department + '\'' +
				", grade='" + grade + '\'' +
				", minMark=" + minMark +
				", maxMark=" + maxMark +
				", descr='" + descr + '\'' +
				", compulsory='" + compulsory + '\'' +
				", examCompulsory='" + examCompulsory + '\'' +
				", subjectColour='" + subjectColour + '\'' +
				", modified=" + modified +
				", modifierDate=" + modifierDate +
				", lastModified=" + lastModified +
				'}';
	}
}
