package data_services.fee_process;

import api.auth.Auth;
import api.util.IDGenerator;
import dao_implimentation.fees.PaymentHistoryDoaQuery;
import dao_implimentation.fees.StdPaymentDoaQuery;
import data_services.actors_record.guardian.GuardianRecord;
import data_services.actors_record.student.StudentRecord;
import data_services.sch_config.SchConfigServices;
import models.education.fees.*;
import models.guardian_record.Guardian;
import models.std_records.Student;
import models_views.FeesObjBinding;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 13/07/2017.
 */
public class FeeServices {

    private StdPaymentDoaQuery stdPaymentDoaQuery;
    private PaymentHistoryDoaQuery paymentHistoryDoaQuery;
    private SchConfigServices schoolConfigView;
    private GuardianRecord guardianRecord;
    private StudentRecord stdRrd;
    private FeesObjBinding feesObjBinding;
    private  boolean checkResult = false;

    @Inject
    public FeeServices(SchConfigServices schoolConfigView,
                       GuardianRecord guardianRecord,
                       StudentRecord stdRrd,
                       StdPaymentDoaQuery stdPaymentDoaQuery,
                       PaymentHistoryDoaQuery paymentHistoryDoaQuery){
        this.schoolConfigView = schoolConfigView;
        this.guardianRecord = guardianRecord;
        this.stdRrd = stdRrd;
        this.stdPaymentDoaQuery = stdPaymentDoaQuery;
        this.paymentHistoryDoaQuery = paymentHistoryDoaQuery;
        this.feesObjBinding = new FeesObjBinding();
    }

    public FeeServices(){}


    public List<StudentFee> studentFeeList(){
        List<Student> student = stdRrd.getAllStudent();
        List<Guardian> gRecord = guardianRecord.getAllGuardian();
        List<FeeDetail> feeDetails = schoolConfigView.getAllFeeDetail(Auth.sessionSchoolId());
        List<StudentPayment> stdPayment = getAllStudentPayment();
        List<StudentFee> stdFee = feesObjBinding.studentFeeBinding(student,gRecord,feeDetails,stdPayment);
        return stdFee;
    }

    public List<StudentPayment> getAllStudentPayment(){
        List<StudentPayment> studentPayments = stdPaymentDoaQuery.findAllById("{schoolId: '"+ Auth.sessionSchoolId()+"'}");
        if(studentPayments == null || studentPayments.size() == 0){
            studentPayments = new ArrayList<>();
        }
        return studentPayments;
    }

    public List<PaymentHistory> getAllPaymentHistories(){
        List<PaymentHistory> paymentHistories = paymentHistoryDoaQuery.findAllById("{schoolId: '"+ Auth.sessionSchoolId()+"'}");
        if(paymentHistories == null || paymentHistories.size() == 0){
            paymentHistories = new ArrayList<>();
        }
        return paymentHistories;
    }

    public boolean insertPaidFee(StudentPayment studentPayment){
            if(studentPayment != null){
                studentPayment._id = IDGenerator.subDocId();
                studentPayment.schoolId = Auth.sessionSchoolId();
                studentPayment.modifier = Auth.sessionUsersId();
                studentPayment.lastModified = new Date();
                studentPayment.modified = new Date();
                stdPaymentDoaQuery.insert(studentPayment);
                checkResult = true;
            }
        return checkResult;
    }
    public boolean updatePaidFee(StudentPayment studentPayment){
        if(studentPayment != null){
            studentPayment.modifier = Auth.sessionUsersId();
            studentPayment.lastModified = new Date();
            studentPayment.modified = new Date();
            stdPaymentDoaQuery.upload("{schoolId: '"+ Auth.sessionSchoolId()+"'}",studentPayment);
            checkResult = true;
        }
        return checkResult;
    }

    public boolean insertPaymentHistories(PaymentHistory paymentHistory){
        if(paymentHistory != null){
            paymentHistory.schoolId = Auth.sessionSchoolId();
            paymentHistory.id = IDGenerator.subDocId();
            paymentHistory.modifier = Auth.sessionUsersId();
            paymentHistory.lastModified = new Date();
            paymentHistory.modified = new Date();
            paymentHistoryDoaQuery.insert(paymentHistory);
            checkResult = true;
        }
        return checkResult;
    }
    public boolean updatePaymentHistories(PaymentHistory paymentHistory){
        if(paymentHistory != null){
            paymentHistory.lastModified = new Date();
            paymentHistory.modifier = Auth.sessionUsersId();
            paymentHistory.modified = new Date();
            paymentHistoryDoaQuery.upload("{schoolId: '"+ Auth.sessionSchoolId()+"'}",paymentHistory);
            checkResult = true;
        }
        return checkResult;
    }

    public PaymentHistory getPaymentHistoryById(long id){
        PaymentHistory payHistory = paymentHistoryDoaQuery.find("{schoolId: '"+ Auth.sessionSchoolId()+"', id: "+id+"}");
        return payHistory;
    }
    public StudentPayment stdPayment(StudentPayment studentPayment){
        return feesObjBinding.studentPaymentPass(studentPayment);
    }

    public PaymentHistory paymentHistory(StudentPayment studentPayment, String receiptNo,String paymentMode, String payBy){
        return feesObjBinding.paymentHistoryPass(studentPayment,receiptNo,paymentMode,payBy);
    }







}
