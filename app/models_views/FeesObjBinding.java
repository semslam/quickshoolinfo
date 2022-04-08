package models_views;

import api.util.Util;
import models.education.fees.*;
import models.guardian_record.Guardian;
import models.std_records.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 13/07/2017.
 */
public class FeesObjBinding {
    private Student std;
    private Guardian gurd;
    private FeeDetail feeDetail;
   // private StudentFee stdFee;
 double fee = 0.0; double paid = 0.0; double disc = 0.0; double due = 0.0; double balance = 0.0;
    public FeesObjBinding(){
        this.std = new Student();
        this.gurd = new Guardian();
        this.feeDetail = new FeeDetail();
       // this.stdFee = new StudentFee();
    }
   // public FeesObjBinding(){}

    public StudentPayment studentPaymentPass(StudentPayment stdPayment){
        stdPayment.feePayments = computForStdPayment(stdPayment.feePayments);
        stdPayment.totalFee = fee;
        stdPayment.totalDiscount = disc;
        stdPayment.totalDue = due;
        stdPayment.totalPaidAmount = paid;
        stdPayment.totalBalance = balance;
        return stdPayment;
    }

    public PaymentHistory paymentHistoryPass(StudentPayment stdPayment, String receiptNo,String paymentMode, String payBy){
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.feePayments = computForPaymtHistory(stdPayment.feePayments);
        paymentHistory.id = stdPayment._id;
        paymentHistory.academicYear = stdPayment.academicYear;
        paymentHistory.studentName = stdPayment.stdName;
        paymentHistory.payBy = payBy;
        paymentHistory.receiptNo = receiptNo;
        paymentHistory.paymentMode = paymentMode;
        paymentHistory.feeAmount = stdPayment.totalFee;
        paymentHistory.feeDiscount = disc;
        paymentHistory.dueFeeAmount = due;
        paymentHistory.paidAmount = paid;
        paymentHistory.balanceAmount = balance;
        paymentHistory.remark = stdPayment.remark;
        return paymentHistory;
    }

    public List<FeePayment> computForStdPayment(List<FeePayment> feePaymentList){
        List<FeePayment> fPayments = new ArrayList<>();
        int ad = 0;
        for (FeePayment feePayment : feePaymentList){
                FeePayment fPayment = new FeePayment();
            System.out.println("\nStdPAid:"+ ad++ +" "+feePayment.feePaidAmount);
            System.out.println("\nStudents Payment: "+feePayment.toString());
                fPayment.id = feePayment.id;
                fPayment.feeName = feePayment.feeName;
                // due = 0.0 = preDue = 0.0 - = newFee 50000.0
                due += fPayment.feeDue = Util.getDouble(Util.initialVal(feePayment.feeDue) - Util.initialVal(feePayment.feePaidAmount));
                System.out.println("\n at the initial stage Total due: "+due +" due: "+ fPayment.feeDue);
                fee += fPayment.feeAmount = feePayment.feeAmount;
                System.out.println("\n at the initial stage Total fee: "+fee +" fee: "+ fPayment.feeAmount);
                // balance = 0.0 = preBalance = 50000.0 - = newFee 50000.0 // (bal - (pay + dis)
                balance += fPayment.feeBalance = Util.getDouble(Util.initialVal(feePayment.feeBalance) - (Util.initialVal(Util.getDouble(feePayment.feePaidAmount) + Util.getDouble(feePayment.feeDiscount) )));
                System.out.println("\n at the initial stage Total balance: "+balance +" balance: "+ fPayment.feeBalance);

                System.out.println("\n "+ ( (feePayment.feeAmount + Util.initialVal(feePayment.feeDue))));
                System.out.println("\n "+ Util.initialVal(feePayment.feeBalance));
                System.out.println((Util.initialVal(feePayment.feePaidAmount) + Util.initialVal(feePayment.feeDiscount)) +" + "+ ( (feePayment.feeAmount + Util.initialVal(feePayment.feeDue)) - Util.initialVal(feePayment.feeBalance)));
                // fee = 60000.0  = previousFee = 10000.0  + = newFee = 50000.0 // pre += ((pay + dis) + ((fee + due) - bal));
                paid += fPayment.feePaidAmount = (Util.initialVal(feePayment.feePaidAmount) + Util.initialVal(feePayment.feeDiscount)) +( (feePayment.feeAmount + Util.initialVal(feePayment.feeDue)) - Util.initialVal(feePayment.feeBalance));
                System.out.println("\n at the initial stage Total Paid: "+paid +" paid: "+ fPayment.feePaidAmount);
                // disc = 20000.0 = preDisc = 70000.0 - = newFee 50000.0
                disc += fPayment.feeDiscount = 0.0;
                System.out.println("\n at the initial stage Total disc: "+disc +" disc: "+ fPayment.feeDiscount);

                System.out.println("Get Students Payment: "+fPayment.toString());
                fPayments.add(fPayment);
        }
        return fPayments;
    }

    public List<FeePayment> computForPaymtHistory(List<FeePayment> feePaymentList){
        List<FeePayment> fPayments = new ArrayList<>();
        for (FeePayment feePayment : feePaymentList){
                FeePayment fPayment = new FeePayment();
                if( feePayment.feePaidAmount != 0.0){
                    System.out.println("PAid: "+feePayment.feePaidAmount);
                    fPayment.id = feePayment.id;
                    fPayment.feeName = feePayment.feeName;
                    // disc = 20000.0 = preDisc = 70000.0 - = newFee 50000.0
                    disc += fPayment.feeDiscount = Util.getDouble(feePayment.feeDiscount);
                    System.out.println("\n at the initial stage Total disc: "+disc +" disc: "+ fPayment.feeDiscount);
                    // due = 0.0 = preDue = 0.0 - = newFee 50000.0
                    due += fPayment.feeDue = Util.getDouble(feePayment.feeDue - Util.initialVal(feePayment.feePaidAmount));
                    System.out.println("\n at the initial stage Total due: "+due +" due: "+ fPayment.feeDue);
                    fee += fPayment.feeAmount = feePayment.feeAmount;
                    System.out.println("\n at the initial stage Total fee: "+fee +" fee: "+ fPayment.feeAmount);
                    // balance = 0.0 = preBalance = 50000.0 - = newFee 50000.0
                    balance += fPayment.feeBalance = Util.getDouble(feePayment.feeBalance - (Util.initialVal(feePayment.feePaidAmount + Util.getDouble(feePayment.feeDiscount) )));
                    System.out.println("\n at the initial stage Total balance: "+balance +" balance: "+ fPayment.feeBalance);
                    // fee = 60000.0  = previousFee = 10000.0  + = newFee = 50000.0
                    paid += fPayment.feePaidAmount = feePayment.feeAmount;
                    System.out.println("\n at the initial stage Total Paid: "+paid +" paid: "+ fPayment.feePaidAmount);

                    System.out.println("Payment Histories: "+fPayment.toString());
                    fPayments.add(fPayment);
                }
        }
        return fPayments;
    }

    public List<StudentFee> studentFeeBinding(List<Student> stds, List<Guardian> guds, List<FeeDetail> feeD, List<StudentPayment> stdPay){
        List<StudentFee> stdFees = new ArrayList<>();
        if(stds != null || stds.size() !=0){
            for(Student student: stds){
              StudentFee stdFee = new StudentFee();
                stdFee.id = student._id;
                stdFee.admissionNo = student.admissionNo;
                stdFee.studentName = student.firstName+" "+ student.lastName;
                stdFee.studentDetails = student.grade +" | "+ student.classRoom;
                if(guds !=null || guds.size() !=0){
                    for(Guardian gu : guds){
                        // write a method that will itetrate tru studentId list
                        System.out.println("StdFee: "+ stdFee.id +" Std: "+student._id);
                        if(Util.equalInList(stdFee.id,gu.studentId)){
                            stdFee.gurdianName = gu.title+" "+ gu.firstName +" "+gu.lastName;
                        }else {
                            stdFee.gurdianName = "N/A";
                        }
                    }
                }
                if(stdPay != null && stdPay.size() != 0){
                    StudentPayment sp = new StudentPayment();
                   for(StudentPayment stp : stdPay){
                       if(stp._id == std._id){
                           sp.totalFee += stp.totalFee;
                           sp.totalDue += stp.totalDue;
                           sp.totalDiscount += stp.totalDiscount;
                           sp.totalPaidAmount += stp.totalPaidAmount;
                           sp.totalBalance += stp.totalBalance;
                       }

                       stdFee.feeAmount = sp.totalFee;
                       stdFee.dueAmount = sp.totalDue;
                       stdFee.feeDiscount = sp.totalDiscount;
                       stdFee.paidAmount = sp.totalPaidAmount;
                       stdFee.balanceAmount = sp.totalBalance;
                   }
                }else if(feeD != null || feeD.size() != 0){
                    FeeDetail f = new FeeDetail();
                    for(FeeDetail fd: feeD){

                        f.feeAmount += fd.feeAmount;
                        f.dueFeeAmount += fd.dueFeeAmount;
                        f.totalAmount += fd.totalAmount;
                    }

                    stdFee.feeAmount = f.totalAmount;
                    stdFee.dueAmount = f.dueFeeAmount;
                    stdFee.feeDiscount = 0.0;
                    stdFee.paidAmount = 0.0;
                    stdFee.balanceAmount = f.totalAmount + f.dueFeeAmount;


                }
                System.out.println("Std Fee: "+stdFee.toString());
                stdFees.add(stdFee);
            }
        }
        return  stdFees;
    }

}
