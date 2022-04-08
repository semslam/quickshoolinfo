package data_services.sch_config;

import api.auth.Auth;
import dao_implimentation.SchConfigDaoQuery;
import models.education.config.*;
import models.education.fees.Fee;
import models.education.fees.FeeDetail;
import models.time_table.TimeTable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Ibrahim Olanrewaju S on 16/10/2016.
 **/

public class SchConfigServices {

    private SchoolConfiguration schConf;
    private SchConfigDaoQuery schConfigDaoQuery;
    private boolean checkResult =  false;


    @Inject
    private SchConfigServices(SchConfigDaoQuery schConfigDaoQuery){
        this.schConfigDaoQuery = schConfigDaoQuery;
        this.schConf = new SchoolConfiguration();
    }

    public SchConfigServices(){}

    public String schoolId;
    public List<Grade> grades = new ArrayList<>();
    public List<ClassRoom> classRoom = new ArrayList<>();
    public List<Term> term = new ArrayList<>();
    public List<Subject> subject = new ArrayList<>();
    public List<Period> periods = new ArrayList<>();
    public List<TimeTable> timeTable = new ArrayList<>();
    public List<Attendance> attendance = new ArrayList<>();
    public List<Fee> fee = new ArrayList<>();
    public List<FeeDetail> feeDetails = new ArrayList<>();
    public List<Position> positions = new ArrayList<>();
    public List<Department> departments = new ArrayList<>();
    public List<ClassRoomType> classRoomTypes = new ArrayList<>();

    public SchoolConfiguration findById(){
      return   schConfigDaoQuery.findOne("{_id: '"+Auth.sessionSchoolId()+"'}");
    }



    public boolean addSchConfAuto(SchConfigServices sc){

        out.println("\n\n Show School Id: "+sc.schoolId);
        schConf._id = sc.schoolId;
        //schConf.grades = sc.grades;
        List<Grade> grades = new ArrayList<>();
        for (Grade g: sc.grades) {
            grades.add(g);
        }
        schConf.grades = grades;
        if(sc.grades.size() != 0 && sc.grades != null){
            out.println(schConfigDaoQuery.toString());
            schConfigDaoQuery.insert(schConf);
            checkResult = true;
        }
        return checkResult;
    }

   public boolean addSchConfig(SchConfigServices schoolConfigs){

       if(schoolConfigs != null ) {
           if (schoolConfigs.grades != null && schoolConfigs.grades.size() !=0 ) {
               grades = schoolConfigs.grades;
               Grade grade = grades.get(grades.size() -1);
               System.out.println(grade.toString());
               String grade1 = "grades.id : "+grades.iterator().next().id+"";
               System.out.println(grade.toString() +" :GRADE: "+grade1);
               checkResult = schConfigDaoQuery.addNewSubDoc("grades",grade);
           }
          else if (schoolConfigs.classRoom.size() != 0 && schoolConfigs.classRoom != null) {
               classRoom = schoolConfigs.classRoom;
               String classR = "classRooms.id : "+classRoom.iterator().next().id+"";
              ClassRoom classRooms = classRoom.get(classRoom.size()-1);
               System.out.println(classRooms.toString());
               checkResult = schConfigDaoQuery.addNewSubDoc("classRooms",classRooms);
           }
          else if (schoolConfigs.periods.size() != 0 && schoolConfigs.periods != null) {
               periods = schoolConfigs.periods;
               Period period = periods.get(periods.size() -1);
               checkResult = schConfigDaoQuery.addNewSubDoc("periods",period);
           }
          else if (schoolConfigs.attendance.size() !=0 && schoolConfigs.attendance != null) {
               attendance = schoolConfigs.attendance;
               Attendance attendances = attendance.get(attendance.size() -1);
               checkResult = schConfigDaoQuery.addNewSubDoc("attendances",attendances);
           }
          else if (schoolConfigs.fee.size() !=0 && schoolConfigs.fee != null) {
               fee = schoolConfigs.fee;
               Fee fees = fee.get(fee.size() -1);
               schConfigDaoQuery.addNewSubDoc("fees",fees);
               checkResult = true;
           }
           else if (schoolConfigs.feeDetails.size() !=0 && schoolConfigs.feeDetails != null) {
               feeDetails = schoolConfigs.feeDetails;
               FeeDetail feeDetail = feeDetails.get(feeDetails.size() -1);
               schConfigDaoQuery.addNewSubDoc("feeDetails",feeDetail);
               checkResult = true;
           }
          else if (schoolConfigs.term.size() !=0 && schoolConfigs.term != null) {
               term = schoolConfigs.term;
               Term terms = term.get(term.size()-1);
               checkResult = schConfigDaoQuery.addNewSubDoc("terms",terms);
           }
          else if (schoolConfigs.timeTable.size() !=0 && schoolConfigs.timeTable != null) {
               timeTable = schoolConfigs.timeTable;
               TimeTable timeTables = timeTable.get(timeTable.size()-1);
               checkResult = schConfigDaoQuery.addNewSubDoc("timeTables",timeTables);
           }
          else if (schoolConfigs.positions.size() !=0 && schoolConfigs.positions != null) {
               positions = schoolConfigs.positions;
               Position position = positions.get(positions.size() -1);
               checkResult = schConfigDaoQuery.addNewSubDoc("positions",position);
           }
           else if (schoolConfigs.subject.size() !=0 && schoolConfigs.subject != null) {
               subject = schoolConfigs.subject;
               Subject subjects = subject.get(subject.size() -1);
               checkResult = schConfigDaoQuery.addNewSubDoc("subjects",subjects);
           }
           else if (schoolConfigs.departments.size() !=0 && schoolConfigs.departments != null) {
               departments = schoolConfigs.departments;
               Department department = departments.get(departments.size() -1);
               checkResult = schConfigDaoQuery.addNewSubDoc("departments",department);
           }
           else if (schoolConfigs.classRoomTypes.size() !=0 && schoolConfigs.classRoomTypes != null) {
               classRoomTypes = schoolConfigs.classRoomTypes;
               ClassRoomType classRoomType = classRoomTypes.get(classRoomTypes.size() -1);
               checkResult = schConfigDaoQuery.addNewSubDoc("classRoomTypes",classRoomType);
           }
       }else{
           checkResult = false;
       }

       return checkResult;
   }

    public boolean uploadSchConfig(SchConfigServices schoolConfigs){

        if(schoolConfigs != null) {
            if ( schoolConfigs.classRoom.size() != 0 && schoolConfigs.classRoom != null) {
                classRoom = schoolConfigs.classRoom;
                ClassRoom classRooms = classRoom.get(classRoom.size()-1);
                String classRoomId = "classRooms.id : "+classRoom.iterator().next().id;
                checkResult = schConfigDaoQuery.uploadSubDocument(classRoomId,"classRooms", classRooms);
            } else if (schoolConfigs.attendance.size() != 0 && schoolConfigs.attendance != null) {
                attendance = schoolConfigs.attendance;
                Attendance attendances = attendance.get(attendance.size() -1);
                String attendanceId  ="attendances.id: "+attendance.iterator().next().id;
                checkResult = schConfigDaoQuery.uploadSubDocument(attendanceId,"attendances", attendances);

            } else if (schoolConfigs.fee.size() != 0 && schoolConfigs.fee != null) {
                fee = schoolConfigs.fee;
                Fee fees  = fee.get(fee.size() -1);
                String feeId = "fees.id : "+fee.iterator().next().id;
                checkResult = schConfigDaoQuery.uploadSubDocument(feeId,"fees", fees);

            } else if (schoolConfigs.feeDetails.size() != 0 && schoolConfigs.feeDetails != null) {
                feeDetails = schoolConfigs.feeDetails;
                FeeDetail feeDetail  = feeDetails.get(feeDetails.size() -1);
                String feeId = "feeDetails.id : "+feeDetails.iterator().next().id;
                checkResult = schConfigDaoQuery.uploadSubDocument(feeId,"feeDetails", feeDetail);

            } else if (schoolConfigs.term.size() != 0 && schoolConfigs.term != null) {
                term = schoolConfigs.term;
                Term terms = term.get(term.size() -1);
                String termId = "terms.id : "+term.iterator().next().id;
                checkResult = schConfigDaoQuery.uploadSubDocument(termId,"terms", terms);

            } else if (schoolConfigs.timeTable.size() != 0 && schoolConfigs.timeTable != null) {
                timeTable = schoolConfigs.timeTable;
                TimeTable timeTables = timeTable.get(timeTable.size() -1);
                String timeTableId = ""+timeTable.iterator().next()._id;
                checkResult = schConfigDaoQuery.uploadSubDocument(timeTableId,"timeTables", timeTables);

            } else if (schoolConfigs.grades.size() != 0 && schoolConfigs.grades != null) {
                grades = schoolConfigs.grades;
                Grade grade = grades.get(grades.size() -1);
                 String gradeId = "grades.id: "+grades.iterator().next().id+"";
                System.out.println(gradeId);
                checkResult  = schConfigDaoQuery.uploadSubDocument(gradeId,"grades", grade);

            } else if (schoolConfigs.positions.size() != 0 && schoolConfigs.positions != null) {
                positions = schoolConfigs.positions;
                Position position = positions.get(positions.size() -1);
                String positionId = "positions.id: "+positions.iterator().next().id;
                 checkResult = schConfigDaoQuery.uploadSubDocument(positionId,"positions",position);

            } else if (schoolConfigs.departments.size() != 0 && schoolConfigs.departments != null) {
                departments = schoolConfigs.departments;
                Department department = departments.get(departments.size() -1);
                String departmentId = "departments._id: "+departments.iterator().next()._id;
                checkResult = schConfigDaoQuery.uploadSubDocument(departmentId,"departments",department);

            }else if (schoolConfigs.classRoomTypes.size() != 0 && schoolConfigs.classRoomTypes != null) {
                classRoomTypes = schoolConfigs.classRoomTypes;
                ClassRoomType classRoomType = classRoomTypes.get(classRoomTypes.size() -1);
                String classRoomTypeId = "classRoomTypes.id: "+classRoomTypes.iterator().next().id;
                checkResult = schConfigDaoQuery.uploadSubDocument(classRoomTypeId,"classRoomTypes",classRoomType);
            }else {
                checkResult = false;
            }
        }else{
            checkResult = false;
        }
        return checkResult;
    }

    public List<ClassRoom> getAllClassRoom(String id){
        List<ClassRoom> classRooms = schConfigDaoQuery.findOne("{_id: '"+id+"'},{classRooms: 1}").classRooms;
        if(classRooms == null || classRooms.size() == 0){
            classRooms = new ArrayList<>();
        }
        return classRooms;
    }
    public List<Attendance> getAllAttendance(String id){
        List<Attendance> attendances = schConfigDaoQuery.findOne("{_id: '"+id+"'},{attendances: 1}").attendances;
        if(attendances == null || attendances.size() == 0){
            attendances = new ArrayList<>();
        }
        return  attendances;
    }
    public List<Fee> getAllFee(String id){
        List<Fee> fees = schConfigDaoQuery.findOne("{_id: '"+id+"'},{fees: 1}").fees;
        if(fees == null || fees.size() == 0){
            fees = new ArrayList<>();
        }
        return fees;
    }

    public List<FeeDetail> getAllFeeDetail(String id){
        List<FeeDetail> feeDetails = schConfigDaoQuery.findOne("{_id: '"+id+"'},{feeDetails: 1}").feeDetails;
        if(feeDetails == null || feeDetails.size() == 0){
            feeDetails = new ArrayList<>();
        }
        return feeDetails;
    }
    public List<Term> getAllTerm(String id){
        List<Term> terms = schConfigDaoQuery.findOne("{_id: '"+id+"'},{terms: 1}").terms;
        if(terms == null || terms.size() == 0){
            terms = new ArrayList<>();
        }
        return terms;
    }
    public List<TimeTable> getAllTimeTable(String id){
        List<TimeTable> timeTables = schConfigDaoQuery.findOne("{_id: '"+id+"'},{timeTables: 1}").timeTables;
        if(timeTables == null || timeTables.size() == 0){
            timeTables = new ArrayList<>();
        }
        return timeTables;
    }
    public List<Grade> getAllGrade(String id){
        List<Grade> grades = schConfigDaoQuery.findOne("{_id: '"+id+"'},{grades: 1}").grades;
        if(grades == null || grades.size() == 0){
            grades = new ArrayList<>();
        }
        return grades;
    }
    public List<Subject> getAllSubject(String id){
        List<Subject> subjects  = schConfigDaoQuery.findOne("{_id: '"+id+"'},{subjects: 1}").subjects;
        if(subjects == null || subjects.size() == 0){
            subjects = new ArrayList<>();
        }
        return subjects;
    }
    public List<Period> getAllPeriod(String id){
        List<Period> periods = schConfigDaoQuery.findOne("{_id: '"+id+"'},{periods: 1}").periods;
        if(periods == null || periods.size() == 0){
            periods = new ArrayList<>();
        }
        return  periods;
    }
    public List<Position> getAllPosition(String id){
        List<Position> positions = schConfigDaoQuery.findOne("{_id: '"+id+"'},{positions: 1}").positions;
        if(positions == null || positions.size() == 0){
            positions = new ArrayList<>();
        }
        return positions;
    }

    public List<Department> getAllDepartment(String id){
        List<Department> departments = schConfigDaoQuery.findOne("{_id: '"+id+"'},{departments: 1}").departments;
        if(departments == null || departments.size() == 0){
            departments = new ArrayList<>();
        }
        return departments;
    }

    public List<ClassRoomType> getAllClassRoomType(String id){
        List<ClassRoomType> classRoomTypes = schConfigDaoQuery.findOne("{_id: '"+id+"'},{classRoomTypes: 1}").classRoomTypes;
        if(classRoomTypes == null || classRoomTypes.size() == 0){
            classRoomTypes = new ArrayList<>();
        }
        return classRoomTypes;
    }

    public Fee feeProcess(Fee fee){
        Fee fe = fee;
        if(fee.feeDuration == null || fee.feeDuration.size() == 0){
            fe.feeDuration = Arrays.asList(fee.feeCollection);
        }
     return fe;
    }

    public FeeDetail feeDetailProcess(FeeDetail feeD){
        int count;
        FeeDetail fDetail = feeD;
        if(getAllFee(Auth.sessionSchoolId()) != null ||  getAllFee(Auth.sessionSchoolId()).size() != 0){
            for(Fee fee : getAllFee(Auth.sessionSchoolId())){
                if(fee.feeName.equals(feeD.feeName)){
                    System.out.println(fee.toString());
                    fDetail.feeId = fee.id;
                    fDetail.feeGroup = fee.feeGroup;
                    fDetail.description = fee.description;
                    fDetail.feeCollection = fee.feeCollection;
                    if(fee.feeDuration != null || fee.feeDuration.size() != 0){
                        count = fee.feeDuration.size();
                        System.out.println(count);
                        fDetail.totalAmount =  feeD.feeAmount * (double) count;
                        fDetail.feeDuration = fee.feeDuration;
                        fDetail.items = count;
                    }
                }
            }
        }
       return fDetail;
    }
}