/* بسم الله الرحمن الرحيم */
package controllers.fees;
/**
 * Created by Ibrahim Olanrewaju AbdulSemiu on 13/07/2017.
 */
import api.auth.Auth;
import data_services.actors_record.student.StudentRecord;
import data_services.fee_process.FeeServices;
import data_services.sch_config.SchConfigServices;
import models.education.fees.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class FeeCtr extends Controller{
    public FeeServices feeServices;
    public StudentRecord stdR;
    private SchConfigServices schoolConfigView;
    private  boolean checkResult = false;

    @Inject
    public FeeCtr(FeeServices feeServices,StudentRecord stdR,SchConfigServices schoolConfigView){
        this.feeServices = feeServices;
        this.stdR = stdR;
        this.schoolConfigView = schoolConfigView;
    }

    public Result studentPayment(long id){
        StudentRecord stdRecord = new StudentRecord();
        List<StudentPayment> studentPayments = new ArrayList<>();
        StudentPayment stdPay = new StudentPayment();
        if(id !=0){
            stdRecord = stdR.findStudentById(id);
            studentPayments = feeServices.getAllStudentPayment();
            if(studentPayments != null && studentPayments.size() != 0){

                for(StudentPayment stdPayment : studentPayments){
                    if(stdPayment.studentId == stdRecord.students._id){
                        // also check if the fee details info as change,
                        stdPay = stdPayment;
                    }
                }
            }else{
                List<FeePayment> feePaymentList = new ArrayList<>();
                for(FeeDetail feeDe : schoolConfigView.getAllFeeDetail(Auth.sessionSchoolId())){
                    if(feeDe.feeDuration != null || feeDe.feeDuration.size() !=0){
                        for(String getV : feeDe.feeDuration){
                            FeePayment feePayment = new FeePayment();
                            feePayment.feeAmount = feeDe.feeAmount;
                            feePayment.feeName = feeDe.feeName +" ("+getV+")";
                            feePayment.feeDue = feeDe.dueFeeAmount;
                            feePayment.feeDiscount = 0.0;
                            feePayment.feePaidAmount = 0.0;
                            feePayment.feeBalance = feeDe.feeAmount + feeDe.dueFeeAmount;
                            feePaymentList.add(feePayment);
                        }
                    }
                    stdPay.academicYear = feeDe.academicYear;
                }
                stdPay.feePayments = feePaymentList;
            }
            stdPay.studentId = stdRecord.students._id;
            stdPay.stdName = stdRecord.students.firstName +" "+ stdRecord.students.firstName;
            stdPay.admissionNo = stdRecord.students.admissionNo;
            stdPay.stdDetails = stdRecord.students.grade +" "+ stdRecord.students.classRoom;

            return ok(views.html.authpage.fee.student_payment.render(stdPay));
        }
        // get from student payment and also from fee details compare if the date is equal if yes
        // get student payment info and display it to the page, else
        // get fee details info and do the math calculation and display it to the page
        // please pay attention to fee detail manipulation when u get the data
        flash().put("error","It can find the student id");
        return redirect(controllers.fees.routes.FeeCtr.paymentFee());
    }

    public Result insertPaymentFee() {
        Form<StudentPayment> stdPayForm = Form.form(StudentPayment.class).bindFromRequest();
        // after submit successful add payment history and display receipt info it should be able to print send sms and email
        String receiptNo = stdPayForm.bindFromRequest().data().get("receiptNo");
        String paymentMode = stdPayForm.bindFromRequest().data().get("paymentMode");
        String payBy = stdPayForm.bindFromRequest().data().get("payBy");

        /*List<Double> prePaidAmount = new ArrayList<>();
        for (String value : request().body().asFormUrlEncoded().get("prePaidAmount")){
            prePaidAmount.add(Double.parseDouble(value));
        }
        List<String> list = Arrays.asList(request().body().asFormUrlEncoded().get("prePaidAmount"));*/

        StudentPayment stdPayment = stdPayForm.get();
        System.out.println(stdPayment.toString());

       StudentPayment studentPayment = feeServices.stdPayment(stdPayment);
        PaymentHistory paymentHistory = feeServices.paymentHistory(stdPayment,receiptNo,paymentMode,payBy);

        System.out.println("\n Student Payment: "+String.format(studentPayment.toString()));
        System.out.println("\n Payment History: "+String.format(paymentHistory.toString()));
        //return TODO;
        boolean checkHistory = false;
        if(stdPayment._id <= 0){
            checkResult = feeServices.insertPaidFee(studentPayment);
            checkHistory = feeServices.insertPaymentHistories(paymentHistory);
        }else{
            checkResult =  feeServices.updatePaidFee(studentPayment);
            checkHistory = feeServices.updatePaymentHistories(paymentHistory);
        }

        if(!checkResult && checkHistory){
            flash().put("error","Payment was not save try again");
            return redirect(controllers.fees.routes.FeeCtr.paymentFee());
        }
        flash().put("success","Payment successfully save");
        return redirect(controllers.fees.routes.FeeCtr.successfulPayment(paymentHistory.id));
        //return redirect(controllers.fees.routes.FeeCtr.paymentHistory());
    }

    public Result successfulPayment(long id){
        if(id <= 0){
            flash().put("error","No Payment Transaction Was Parform");
            return redirect(controllers.fees.routes.FeeCtr.paymentFee());
        }
        return ok(views.html.authpage.fee.successful_payment.render(feeServices.getPaymentHistoryById(id)));
    }

    public Result paymentFee(){
        List<StudentFee> studentFees = feeServices.studentFeeList();
        return ok(views.html.authpage.fee.payment_fee.render(studentFees));
    }

    public Result paymentHistory(){
        List<PaymentHistory> paymentHistories = feeServices.getAllPaymentHistories();
        return ok(views.html.authpage.fee.payment_history.render(paymentHistories));
    }

    public Result paymentInvoice(){
        return ok(views.html.authpage.fee.payment_invoice.render());
    }
}
