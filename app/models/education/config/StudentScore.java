package models.education.config;

import models.MyModel;

import java.util.Date;

public class StudentScore extends MyModel{
	
	public String scoreId;
	public String subject;
	public String type;// test homework exam
	public int score;
	public int minscore;
	public int maxscore;
	public Date date;
	public String scoreStatus; // pass or fail

}