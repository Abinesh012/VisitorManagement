package com.quinbay.visitors.service;

import com.quinbay.visitors.helpers.GetNotVisitedPreApprovers;
import com.quinbay.visitors.helpers.GetRecordsByDate;
import com.quinbay.visitors.helpers.NewPreApproval;
import com.quinbay.visitors.interfaces.PreApprovalInterface;
import com.quinbay.visitors.models.*;
import com.quinbay.visitors.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreApprovalService implements PreApprovalInterface {

    @Autowired
    PreApprovalRepository preApprovalRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RequestRepository requestRepository;

//    @Override
//    public String createPreApproval(PreApproval preApproval) {
//        try{
//            PreApproval _pre = preApprovalRepository.save(preApproval);
//            return "PreApproval created successfully!!!";
//        } catch(Exception e){
//            return "PreApproval cannot be created!!!";
//        }
//    }



    @Override
    public List<PreApproval> allPreApprovals() {
        return preApprovalRepository.findAll();
    }

//    @Override
//    public ResponseEntity createPreApproval(int userId, int visitorId, String purpose, String date, String fromTime, String toTime, String type) {
//        Optional<Visitors> visitor = visitorRepository.findById(visitorId);
//        String name = visitor.get().getName();
//
//        Record newRecord = new Record();
//        newRecord.setName(name);
//        newRecord.setVid(visitorId);
//        newRecord.setWhomtovisit(userId);
//        newRecord.setDate(date);
//        newRecord.setPurpose(purpose);
//        newRecord.setPreapproval(true);
//        newRecord.setApprovedby(userId);
//        newRecord.setIntime(fromTime);
//        newRecord.setOuttime(toTime);
//        recordRepository.save(newRecord);
//
//        Optional<Record> currRecord = recordRepository.findByName(name);
//        PreApproval newPreApproval=new PreApproval();
//        newPreApproval.setRecordId(currRecord.get().getRecordid());
//        newPreApproval.setFromTime(fromTime);
//        newPreApproval.setToTime(toTime);
//        newPreApproval.setDate(date);
//        newPreApproval.setVisitorId(visitorId);
//        newPreApproval.setType(type);
//        preApprovalRepository.save(newPreApproval);
//
//        return new ResponseEntity("success", HttpStatus.OK);
//    }
    @Override
    public ResponseEntity createPreApproval(NewPreApproval newPreApproval) {
        try{
            int visitorId=newPreApproval.getVisitorId();
            Optional<Visitors> visitor = visitorRepository.findById(visitorId);
            String name = visitor.get().getName();

            Record newRecord = new Record();
            newRecord.setName(name);
            newRecord.setVid(visitorId);
            newRecord.setDate(newPreApproval.getDate());
            newRecord.setPurpose(newPreApproval.getPurpose());
            newRecord.setPreapproval(true);
            newRecord.setApprovedby(newPreApproval.getUserId());
//            newRecord.setIntime(newPreApproval.getFromTime());
//            newRecord.setOuttime(newPreApproval.getToTime());
            newRecord.setWhomtovisit(newPreApproval.getUserId());
            recordRepository.save(newRecord);

            int recordId=newRecord.getRecordid();
            Optional<Record> currRecord = recordRepository.findById(recordId);

            PreApproval newPreapprovalRecord=new PreApproval();
            newPreapprovalRecord.setRecordId(recordId);
            newPreapprovalRecord.setFromTime(newPreApproval.getFromTime());
            newPreapprovalRecord.setToTime(newPreApproval.getToTime());
            newPreapprovalRecord.setDate(newPreApproval.getDate());
            newPreapprovalRecord.setVisitorId(visitorId);
            newPreapprovalRecord.setType(newPreApproval.getType());
            newPreapprovalRecord.setEmployeeId(newPreApproval.getUserId());
            preApprovalRepository.save(newPreapprovalRecord);
            return new ResponseEntity("success", HttpStatus.OK);


        }catch (Exception e){
        }


        return null;
    }

    @Override
    public ResponseEntity <PreApproval> updateVisitedStatus(int recordId) {
        Optional<PreApproval> preApproval=preApprovalRepository.findByRecordId(recordId);
        preApproval.get().setHasVisited(true);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @Override
    public List <GetNotVisitedPreApprovers> getNotVisitedPreApprovers() {

        List <GetNotVisitedPreApprovers> preApproval=new ArrayList <>();
        try {
            List <PreApproval> preApprovals = preApprovalRepository.findByHasVisitedIsFalse();
            for (PreApproval p : preApprovals) {
                GetNotVisitedPreApprovers getNotVisitedPreApprovers = new GetNotVisitedPreApprovers();
                int preApprovalId = p.getPreApprovalId();
                int visitorId = p.getVisitorId();
                Optional <Visitors> currVisitor = visitorRepository.findById(visitorId);
                Optional <Record> currRecord = recordRepository.findById(p.getRecordId());
                Optional <Users> currUser = userRepository.findById(currRecord.get().getWhomtovisit());
                getNotVisitedPreApprovers.setPreApprovalId(preApprovalId);
                getNotVisitedPreApprovers.setVisitorId(currVisitor.get().getVisitorId());
                getNotVisitedPreApprovers.setVisitorName(currVisitor.get().getName());
                getNotVisitedPreApprovers.setFromTime(p.getFromTime());
                getNotVisitedPreApprovers.setToTime(p.getToTime());
                getNotVisitedPreApprovers.setType(p.getType());
                getNotVisitedPreApprovers.setPurpose(currRecord.get().getPurpose());
                getNotVisitedPreApprovers.setRecordId(currRecord.get().getRecordid());
                getNotVisitedPreApprovers.setEmployeeName(currUser.get().getName());
                getNotVisitedPreApprovers.setEmployeeId(currUser.get().getId());
                preApproval.add(getNotVisitedPreApprovers);
            }

            return preApproval;
        }catch (Exception e){
            return preApproval;

        }
    }

    @Override
    public List <GetRecordsByDate> getPreVisitorsByDate(String date) {
        List<PreApproval> preApprovals = preApprovalRepository.findByDate(date);

        List<GetRecordsByDate> recordsByDate=new ArrayList <>();
        try{
            for(PreApproval p: preApprovals){

                Optional<Record> record=recordRepository.findByRecordid(p.getRecordId());

                if(record.get().getIntime()==null && record.get().getOuttime()==null) {

                    GetRecordsByDate getRecordsByDate = new GetRecordsByDate();
                    int preApprovalId = p.getPreApprovalId();
                    int visitorId = p.getVisitorId();
                    Optional <Visitors> currVisitor = visitorRepository.findById(visitorId);
                    Optional <Record> currRecord = recordRepository.findById(p.getRecordId());
                    int employeeId = currRecord.get().getWhomtovisit();
                    Optional <Users> currUser = userRepository.findById(employeeId);
                    getRecordsByDate.setPreApprovalId(preApprovalId);
                    getRecordsByDate.setVisitorid(currVisitor.get().getVisitorId());
                    getRecordsByDate.setVisitorname(currVisitor.get().getName());
                    getRecordsByDate.setIntime(p.getFromTime());
                    getRecordsByDate.setOuttime(p.getToTime());
                    getRecordsByDate.setType(p.getType());
                    getRecordsByDate.setPurpose(currRecord.get().getPurpose());
                    getRecordsByDate.setRecordid(currRecord.get().getRecordid());
                    getRecordsByDate.setEmpname(currUser.get().getName());
                    getRecordsByDate.setEmployeeid(currUser.get().getId());
                    getRecordsByDate.setPhone(currVisitor.get().getPhone());
                    recordsByDate.add(getRecordsByDate);
                }
            }
            return  recordsByDate;

        }catch (Exception e){

            return  recordsByDate;
       }
   }

}
