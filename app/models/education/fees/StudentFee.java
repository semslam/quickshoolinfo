/* بسم الله الرحمن الرحيم */
package models.education.fees;

import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju Adulsemiu on 05/07/2017.
 */
public class StudentFee {
    public long id;
    public String admissionNo;
    public String schoolId;
    public String AcedemicYear;
    public long studentId;
    public String studentName;
    public String gurdianName;
    public String studentDetails; // append grade and class together
    public double feeAmount;
    public double dueAmount;
    public double feeDiscount;
    public double balanceAmount;
    public double paidAmount;
    public long modifier;
    public Date modified;
    public Date lastModified;
    public int counter;

    @Override
    public String toString() {
        return "StudentFee{" +
                "id=" + id +
                ", admissionNo='" + admissionNo + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", AcedemicYear='" + AcedemicYear + '\'' +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", gurdianName='" + gurdianName + '\'' +
                ", studentDetails='" + studentDetails + '\'' +
                ", feeAmount=" + feeAmount +
                ", dueAmount=" + dueAmount +
                ", feeDiscount=" + feeDiscount +
                ", balanceAmount=" + balanceAmount +
                ", paidAmount=" + paidAmount +
                ", modifier=" + modifier +
                ", modified=" + modified +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
