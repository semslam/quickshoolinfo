/* بسم الله الرحمن الرحيم */
package models.education.fees;

import org.jongo.marshall.jackson.oid.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Adbulsemiu on 11/07/2017.
 */
public class StudentPayment {
    @Id
    public long _id;
    public String schoolId;
    // add admission no and std name and std grade, class together
    public long studentId;
    public String academicYear;
    public String admissionNo;
    public String stdName;
    public String stdDetails;
    public List<FeePayment> feePayments;
    public double totalFee; // get totalAmount from FeePayments
    public double totalDue;
    public double totalDiscount;
    public double totalPaidAmount;
    public double totalBalance;
    public String remark;
    public long modifier;
    public Date modified;
    public Date lastModified;
    public int counter;

    @Override
    public String toString() {
        return "StudentPayment{" +
                "id=" + _id +
                ", schoolId='" + schoolId + '\'' +
                ", studentId=" + studentId +
                ", academicYear='" + academicYear + '\'' +
                ", admissionNo='" + admissionNo + '\'' +
                ", stdName='" + stdName + '\'' +
                ", stdDetails='" + stdDetails + '\'' +
                ", feePayments=" + feePayments +
                ", totalFee=" + totalFee +
                ", totalDue=" + totalDue +
                ", totalDiscount=" + totalDiscount +
                ", totalPaidAmount=" + totalPaidAmount +
                ", totalBalance=" + totalBalance +
                ", remark='" + remark + '\'' +
                ", modifier=" + modifier +
                ", modified=" + modified +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
