package com.quinbay.visitors.service;

import com.quinbay.visitors.helpers.*;
import com.quinbay.visitors.interfaces.RequestInterface;
import com.quinbay.visitors.models.*;
import com.quinbay.visitors.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RequestService implements RequestInterface {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PreApprovalRepository preApprovalRepository;

    @Override
    public String createRequest(int visitorid, String whomtovisit, String purpose) {
        return null;
    }




    @Override
    public List <GetbyWaiting> getByWaiting() {

        List <GetbyWaiting> getByWaiting = new ArrayList <>();
//        List<Record> records = recordRepository.findByIntimeIsNotNullAndOuttimeIsNotNull();
        try {
            //List<Record> records = recordRepository.findByIntimeIsNotNullAndOuttimeIsNotNull();
            List <Record> records = recordRepository.findAll();
             for (Record r : records) {
               if (r.isPreapproval()==false) {
                    int recordId = r.getRecordid();
                    GetbyWaiting gbw = new GetbyWaiting();
                    Optional <Request> request = requestRepository.findByRecordId(recordId);
                   System.out.println( request);
                   System.out.println(request.get().getStatus());

                    int visitorid = request.get().getVisitorId();
                    int employeeid = request.get().getEmployeeId();

                    Optional <Visitors> visitors = visitorRepository.findById(visitorid);
                    Optional <Users> users = userRepository.findById(employeeid);
                    Optional <Record> record1 = recordRepository.findByRecordid(recordId);


                    gbw.setEmployeeid(request.get().getEmployeeId());
                    gbw.setVisitorid(request.get().getVisitorId());
                    gbw.setVisitorname(visitors.get().getName());
                    gbw.setEmployeeid(request.get().getRecordId());
                    gbw.setEmpname(users.get().getName());
                    gbw.setRequestid(request.get().getRequestId());
                    gbw.setRecordid(recordId);
                    gbw.setStatus(request.get().getStatus());
                    gbw.setPurpose(record1.get().getPurpose());

                    if (Objects.equals(request.get().getStatus(), "Disapprove") ||
                            Objects.equals(request.get().getStatus(), "Waiting") ||
                            Objects.equals(request.get().getStatus(), "Approved")) {

                        getByWaiting.add(gbw);
                    }
                 //return getByWaiting;

             }
             }return getByWaiting;
       }
        catch (Exception e)
        {
            return getByWaiting;
        }

    }




    @Override
    public List <GetCurrentvisitors> getByApproved() {

        List<Record> record = recordRepository.findByIntimeIsNotNullAndOuttimeIsNull();
        //List<Record> record=recordRepository.findAll();
        List<GetCurrentvisitors> getCurrentvisitor = new ArrayList <>();
        try {
            for (Record r : record) {
                if(r.isPreapproval()==false) {
                    GetCurrentvisitors getCurrentvisitors = new GetCurrentvisitors();
                    int id = r.getRecordid();
                    Optional <Request> request = requestRepository.findByRecordId(id);

                    int visitorid = request.get().getVisitorId();
                    int employeeid = request.get().getEmployeeId();

                    Optional <Visitors> visitors = visitorRepository.findById(visitorid);
                    Optional <Users> users = userRepository.findById(employeeid);
                    Optional <Record> record1 = recordRepository.findByRecordid(id);

                    getCurrentvisitors.setEmployeeid(request.get().getEmployeeId());
                    getCurrentvisitors.setVisitorid(request.get().getVisitorId());
                    getCurrentvisitors.setVisitorname(visitors.get().getName());
                    getCurrentvisitors.setEmpname(users.get().getName());
                    getCurrentvisitors.setRequestid(request.get().getRequestId());
                    getCurrentvisitors.setRecordid(id);
                    getCurrentvisitors.setStatus(request.get().getStatus());
                    getCurrentvisitors.setPurpose(record1.get().getPurpose());
                    getCurrentvisitors.setIntime(record1.get().getIntime());
                    getCurrentvisitor.add(getCurrentvisitors);
                }else{

                    GetCurrentvisitors getCurrentvisitors = new GetCurrentvisitors();
                    int recordId = r.getRecordid();
                    Optional <Record> record1 = recordRepository.findByRecordid(recordId);
                    int visitorId=record1.get().getVid();
                    int employeeId=record1.get().getWhomtovisit();
                    Optional <Visitors> visitors = visitorRepository.findById(visitorId);
                    Optional <Users> users = userRepository.findById(employeeId);

                    getCurrentvisitors.setEmployeeid(employeeId);
                    getCurrentvisitors.setVisitorid(visitorId);
                    getCurrentvisitors.setVisitorname(visitors.get().getName());
                    getCurrentvisitors.setEmployeeid(employeeId);
                    getCurrentvisitors.setEmpname(users.get().getName());
                    getCurrentvisitors.setStatus("Approved");
                    getCurrentvisitors.setRecordid(recordId);
//                    getCurrentvisitors.setRequestid(0);
                    getCurrentvisitors.setPurpose(record1.get().getPurpose());
                    getCurrentvisitors.setIntime(record1.get().getIntime());
                    getCurrentvisitor.add(getCurrentvisitors);

                }
            }
            return getCurrentvisitor;
        }catch (Exception e){
            return getCurrentvisitor;
        }
    }



    @Override
    public List <ReqByStatus> getRequestByStatus(int employeeId, String status) {
        List<ReqByStatus> reqList = new ArrayList <>();
        List<Request> requests=requestRepository.findByEmployeeIdAndStatus(employeeId,status);
        for(Request r: requests){
            int recordid=r.getRecordId();
            ReqByStatus reqObj  = new ReqByStatus();
            Optional<Record> record=recordRepository.findById(recordid);
            try{
                String name=record.get().getName();
                String purpose=record.get().getPurpose();
                int visitorid=record.get().getVid();
                Optional<Visitors> visitor = visitorRepository.findById(visitorid);
                Long phoneNumber = visitor.get().getPhone();
                reqObj.setRecordid(r.getRecordId());
                reqObj.setRequestid(r.getRequestId());
                reqObj.setName(name);
                reqObj.setNumber(phoneNumber);
                reqObj.setPurpose(purpose);
                reqList.add(reqObj);
            }catch (Exception e){
            }
        }
        return reqList;

//        List<Record> record=recordRepository.findByWhomtovisit(employeeid);
//        List<Request> reqList = new ArrayList <>();
//        for(Record r :record){
//            int id = r.getRecordid();
//            Optional<Request> request = requestRepository.findByRecordid(id);
//            if(request.get().getStatus().equals(status)){
//                reqList.add(request.get());
//            }
//        }
    }

    @Override
    public List <Request> getByCheckedOut() {
        List <Request> reqList = new ArrayList <>();

        try {
            List <Record> record = recordRepository.findByIntimeIsNotNullAndOuttimeIsNotNull();
            for (Record r : record) {
                int id = r.getRecordid();
                Optional <Request> request = requestRepository.findByRecordId(id);
                reqList.add(request.get());
            }
            return reqList;
        }catch (Exception e){
            return  reqList;
        }

    }

    @Override
    public List <GetRecordData> getRecordData() {
        List<Record> record =recordRepository.findAll();
        List<GetRecordData> getRecordData = new ArrayList <>();

        for(Record r: record ){
            GetRecordData getRecDat=new GetRecordData();
//            int requestId=r.getRequestId();
            int recordId=r.getRecordid();
            int employeeId=r.getWhomtovisit();
            int visitorId=r.getVid();

            Optional<Visitors> visitors=visitorRepository.findById(visitorId);
            Optional<Users>users=userRepository.findById(employeeId);
            Optional<Record>record1=recordRepository.findById(recordId);
            if(record1.get().getIntime() != null && record1.get().getOuttime()!= null) {
//                getRecDat.setRequestid(requestId);
                getRecDat.setRecordid(recordId);
                getRecDat.setEmployeeid(employeeId);
                getRecDat.setVisitorid(visitorId);
                getRecDat.setVisitorname(visitors.get().getName());
                getRecDat.setPhone(visitors.get().getPhone());
                getRecDat.setEmpname(users.get().getName());
                getRecDat.setPurpose(record1.get().getPurpose());
                getRecDat.setDate(record1.get().getDate());
                getRecDat.setIntime(record1.get().getIntime());
                getRecDat.setOuttime(record1.get().getOuttime());
//                getRecDat.setStatus(r.getStatus());
                getRecordData.add(getRecDat);
            }
        }

    return getRecordData;
    }

    @Override
    public List <GetRecordData> getRecordDataById(int employeeId) {
        List <GetRecordData> getRecordData = new ArrayList <>();

        try {
            List <Record> records = recordRepository.findByWhomtovisit(employeeId);

            for (Record r : records) {
                GetRecordData getRecDat = new GetRecordData();
//                int requestId = r.getRequestId();
                int recordId = r.getRecordid();
                //int employeeId=r.getEmployeeId();
                int visitorId = r.getVid();
                Optional <Visitors> visitors = visitorRepository.findById(visitorId);
                Optional <Users> users = userRepository.findById(employeeId);
                Optional <Record> record = recordRepository.findById(recordId);
                if (record.get().getIntime() != null && record.get().getOuttime() != null) {
//                    getRecDat.setRequestid(requestId);
                    getRecDat.setRecordid(recordId);
                    getRecDat.setEmployeeid(employeeId);
                    getRecDat.setVisitorid(visitorId);
                    getRecDat.setVisitorname(visitors.get().getName());
                    getRecDat.setPhone(visitors.get().getPhone());
                    getRecDat.setEmpname(users.get().getName());
                    getRecDat.setPurpose(record.get().getPurpose());
                    getRecDat.setDate(record.get().getDate());
                    getRecDat.setIntime(record.get().getIntime());
                    getRecDat.setOuttime(record.get().getOuttime());
//                    getRecDat.setStatus(r.getStatus());
                    getRecordData.add(getRecDat);
                }
            }
            return getRecordData;

        }catch (Exception e){
            return getRecordData;
        }
    }

    @Override
    public List <GetRecordData> getRecordDataByDate(String date) {
        List<GetRecordData> getRecordData = new ArrayList <>();
        try {
            List <Record> record = recordRepository.findAll();
            for (Record r : record) {
                GetRecordData getRecDat = new GetRecordData();
                if (r.getDate().equals(date) && !r.isPreapproval()) {
                    int recordId = r.getRecordid();
                    Optional <Request> request = requestRepository.findByRecordId(recordId);
                    int visitorId = request.get().getVisitorId();
                    int employeeId = request.get().getEmployeeId();
                    Optional <Visitors> visitors = visitorRepository.findById(visitorId);
                    Optional <Users> users = userRepository.findById(employeeId);
                    Optional <Record> record1 = recordRepository.findById(recordId);
                    if (record1.get().getIntime() != null && record1.get().getOuttime() != null) {
                        getRecDat.setRequestid(request.get().getRequestId());
                        getRecDat.setRecordid(recordId);
                        getRecDat.setEmployeeid(employeeId);
                        getRecDat.setVisitorid(visitorId);
                        getRecDat.setVisitorname(visitors.get().getName());
                        getRecDat.setPhone(visitors.get().getPhone());
                        getRecDat.setEmpname(users.get().getName());
                        getRecDat.setPurpose(record1.get().getPurpose());
                        getRecDat.setDate(record1.get().getDate());
                        getRecDat.setIntime(record1.get().getIntime());
                        getRecDat.setOuttime(record1.get().getOuttime());
                        getRecDat.setStatus(request.get().getStatus());
                        getRecordData.add(getRecDat);
                    }
                } else if (r.getDate().equals(date) && r.isPreapproval()) {
                    int recordId = r.getRecordid();
                    int visitorId = r.getVid();
                    int employeeId = r.getWhomtovisit();
                    Optional <Visitors> visitors = visitorRepository.findById(visitorId);
                    Optional <Users> users = userRepository.findById(employeeId);
                    Optional <Record> record1 = recordRepository.findById(recordId);
                    if (record1.get().getIntime() != null && record1.get().getOuttime() != null) {
                        getRecDat.setRecordid(recordId);
                        getRecDat.setEmployeeid(employeeId);
                        getRecDat.setVisitorid(visitorId);
                        getRecDat.setVisitorname(visitors.get().getName());
                        getRecDat.setPhone(visitors.get().getPhone());
                        getRecDat.setEmpname(users.get().getName());
                        getRecDat.setPurpose(record1.get().getPurpose());
                        getRecDat.setDate(record1.get().getDate());
                        getRecDat.setIntime(record1.get().getIntime());
                        getRecDat.setOuttime(record1.get().getOuttime());
                        getRecDat.setStatus("Visited");
                        getRecordData.add(getRecDat);
                    }
                }
            }
            return getRecordData;
        }catch (Exception e){
            return getRecordData;
        }
    }


    @Override
    public List <GetStatistics> getStatistics(String date) {
        List<GetStatistics> getStats = new ArrayList <>();
        //String date="2023-02-05";

        List<PreApproval> preApproval =preApprovalRepository.findAll();
        GetStatistics getStatistics=new GetStatistics();
        int recCount=0;
        for(PreApproval preApp:preApproval){
            if(!preApp.isHasVisited()){
                continue;
            }
            else{
                recCount++;
            }
        }
        List<Request> request=requestRepository.findAll();
        for(Request req: request){
            if(req.getStatus().equals("Disapproved")){
                continue;
            }
            else{
                recCount++;
            }
        }
        getStatistics.setVisitorsTillDate(recCount);

        List<Record> records=recordRepository.findByIntimeIsNotNullAndOuttimeIsNull();

        int notCheckedOut=records.size();
        getStatistics.setNotCheckedOut(notCheckedOut);


        List<Record> records1=recordRepository.findByDate(date);
        int todaysVisitorCount=records1.size();
        getStatistics.setTodayVisitorCount(todaysVisitorCount);

        getStats.add(getStatistics);

        return getStats;
    }

//    @Override
//    public List <Request> getByDisapproved() {
//        //WaitingStatus waitingStatus = new WaitingStatus();
//        return requestRepository.findAllByStatusDisapproved();
//    }

//    @Override
//    public Request getWaitingVisitors(int employeeid) {
//        //Request request=null;
//        Optional<Request> req =requestRepository.findByEmployeeid(employeeid);
//        List <Optional <Request>> reqList = new ArrayList <>();
////
////        for(Request request : req){
////            if(req.get().getStatus() == "Waiting"){
////                reqList.add(req);
////            }
////        }
//        return null;
//
//    }



    @Override
    public ResponseEntity<Request> changeStatus(int requestId, String status) {
        Optional<Request> request=requestRepository.findByRequestId(requestId);
        request.get().setStatus(status);
        requestRepository.save(request.get());
        return new ResponseEntity("Successfully update",HttpStatus.OK);
    }



    @Override
    public List <Request> getAllRequests() {
        return requestRepository.findAll();
    }
}


//    @Override
//    public String createRequest(Request request) {
//
//        try{
//            Request _req = requestRepository.save(request);
//            return "Request created successfully!!!";
//        } catch(Exception e){
//            return "Request cannot be created!!!";
//        }
//    }



