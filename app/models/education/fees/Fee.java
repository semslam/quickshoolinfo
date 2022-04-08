/* بسم الله الرحمن الرحيم */

package models.education.fees;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu  on 05/07/2017.
 */

import java.util.Date;
import java.util.List;

public class Fee {
	
	public long id;
	public String feeName;
	public String feeGroup;// to grade, to department, to all
	public String feeCollection; //1st term , 2nd team, 3rd term, yearly, only once, one month, two month eg
	public List<String> feeDuration; // select period wise e.g, Jan, Feb, March, April etc
	public String description;
	public long modifier;
	public Date modified;
	public Date lastModified;
	public int counter;

    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", feeName='" + feeName + '\'' +
                ", feeGroup='" + feeGroup + '\'' +
                ", feeCollection='" + feeCollection + '\'' +
                ", feeDuration=" + feeDuration +
                ", description='" + description + '\'' +
                ", modifier=" + modifier +
                ", modified=" + modified +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
