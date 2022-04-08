/* بسم الله الرحمن الرحيم */
package models.education.config;
import models.MyModel;
import java.util.Date;

public class Term{
	public long id;
	public String name; // e.g first term , second term, third term
	public String academicSession;
	public Date start;
	public Date end;
	public int weeks;
	public long modifier;
	public Date modified;
	public Date lastModified;
	public int counter;
}
