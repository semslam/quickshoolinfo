package models.education.config;

import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju S on 09/01/2017.
 */
public class Payment {

    public String paymentID;
    public String studentID;
    public String feeID;
    public String paymentMode;// cash3
    public double amountPaid;
    public double balance;
    public String payer;
    public String tellerNum;
    public String paymentNote;
    public Date paymentDate;
    public String modifier;
    public Date modified;
    public Date lastModified;
    public int counter;
}
