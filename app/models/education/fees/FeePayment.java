/* بسم الله الرحمن الرحيم */
package models.education.fees;

import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Adulsemiu on 05/07/2017.
 */
// FeePayment is a sub class StudentPayment class
public class FeePayment {
    public long id;
    public String feeName;
    public double feeAmount;
    public double feeDiscount;
    public double feeDue;
    public Double feePaidAmount = 0.0;
    public double feeBalance;
    public double prePaidAmount;
    /*public long modifier;
    public Date modified;
    public Date lastModified;
    public int counter;*/

    @Override
    public String toString() {
        return "FeePayment{" +
                "id=" + id +
                ", feeName='" + feeName + '\'' +
                ", feeAmount=" + feeAmount +
                ", feeDiscount=" + feeDiscount +
                ", feeDue=" + feeDue +
                ", feePaidAmount=" + feePaidAmount +
                ", feeBalance=" + feeBalance +
                '}';
    }
}
