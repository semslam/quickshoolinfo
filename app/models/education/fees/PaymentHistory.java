/* بسم الله الرحمن الرحيم */
package models.education.fees;

import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Adulsemiu on 05/07/2017.
 */
public class PaymentHistory {
    public long id;
    public String schoolId;
    public String academicYear;
    public String receiptNo;
    public long studentId;
    public String admissionNo;
    public String studentName;
    public String payBy;
    public String paymentMode;
    public List<FeePayment> feePayments;
    public double feeAmount;
    public double dueFeeAmount;
    public double feeDiscount;
    public double paidAmount;
    public double balanceAmount;
    public String remark;
    public long modifier;
    public Date modified;
    public Date lastModified;
    public int counter;

    @Override
    public String toString() {
        return "PaymentHistory{" +
                "id=" + id +
                ", schoolId='" + schoolId + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", receiptNo='" + receiptNo + '\'' +
                ", studentId=" + studentId +
                ", admissionNo='" + admissionNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", payBy='" + payBy + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", feePayments=" + feePayments +
                ", feeAmount=" + feeAmount +
                ", dueFeeAmount=" + dueFeeAmount +
                ", feeDiscount=" + feeDiscount +
                ", paidAmount=" + paidAmount +
                ", balanceAmount=" + balanceAmount +
                ", remark='" + remark + '\'' +
                ", modifier=" + modifier +
                ", modified=" + modified +
                ", lastModified=" + lastModified +
                ", counter=" + counter +
                '}';
    }
}
