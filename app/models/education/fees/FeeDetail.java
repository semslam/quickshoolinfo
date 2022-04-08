/* بسم الله الرحمن الرحيم */
package models.education.fees;

import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu  on 05/07/2017.
 */
public class FeeDetail {

    public long id;
    public String academicYear;
    public long feeId;
    public String feeName;
    public String grades; // jss1 jss2// change this to list
    public double feeAmount;
    public double dueFeeAmount;// late fee
    public Date dueDate;
    public double totalAmount;// sum the feeAmount with number count of feeDuration together; that will be used as feeAmount all over
    public String feeGroup;
    public String feeCollection; //1st term , 2nd team, 3rd term, yearly, only once, one month, two month eg
    public int items;
    public List<String> feeDuration; // select period wise e.g, Jan, Feb, March, April etc
    public String description;
    public long modifier;
    public Date modified;
    public Date lastModified;
    public int counter;

    @Override
    public String toString() {
        return "FeeDetail{" +
                "id=" + id +
                ", academicYear='" + academicYear + '\'' +
                ", feeId=" + feeId +
                ", feeName='" + feeName + '\'' +
                ", grades='" + grades + '\'' +
                ", feeAmount=" + feeAmount +
                ", dueFeeAmount=" + dueFeeAmount +
                ", dueDate=" + dueDate +
                ", totalAmount=" + totalAmount +
                ", feeGroup='" + feeGroup + '\'' +
                ", feeCollection='" + feeCollection + '\'' +
                ", items=" + items +
                ", feeDuration=" + feeDuration +
                ", description='" + description + '\'' +
                ", modifier=" + modifier +
                ", modified=" + modified +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
