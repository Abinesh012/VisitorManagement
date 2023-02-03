package com.quinbay.visitors.service;

import com.quinbay.visitors.helpers.GetCommentsByEmployeeId;
import com.quinbay.visitors.interfaces.RecordInterface;
import com.quinbay.visitors.models.Record;
import com.quinbay.visitors.models.Request;
import com.quinbay.visitors.models.Visitors;
import com.quinbay.visitors.repository.RecordRepository;
import com.quinbay.visitors.repository.RequestRepository;
import com.quinbay.visitors.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class RecordService implements RecordInterface {
    private static Object LocalTime;
    private static final Object Time = LocalTime;
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    RequestRepository requestRepository;

 //   @Override
//    public String createRecord(Record record){
//        try{
//            Record _r = recordRepository.save(record);
//            return "Record created successfully!!!";
//        } catch(Exception e){
//            return "Record cannot be created!!!";
//        }
//    }

    @Override
    public List <Record> getAllrecord() {
        return recordRepository.findAll();
    }

    @Override
    public ResponseEntity<Record> getRecord(int recordId) {
        Optional<Record> recordData = recordRepository.findById(recordId);
        if(recordData.isPresent()){
            return new ResponseEntity<>(recordData.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity <HttpStatus> deleteRecord(int recordId) {
        try{
            recordRepository.deleteById(recordId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateUser(int userId, String username, String emailId, String jobPosition) {
        return null;
    }

//    @Override
//    public ResponseEntity createRecord(int visitorid, int whomtovisit, String purpose, LocalDate date) {
//        return null;
//    }

//    @Override
//    public ResponseEntity updateUser(int userId, String username, String emailId, String jobPosition, LocalDate date) {
//        return null;
//    }

    @Override
    public ResponseEntity createRecord(int visitorId, int whomToVisit, String purpose,String date) {
        Optional<Visitors> visitor = visitorRepository.findById(visitorId);
        String name = visitor.get().getName();

        Record newRecord = new Record();
        newRecord.setName(name);
        newRecord.setPurpose(purpose);
        newRecord.setVid(visitorId);
        newRecord.setWhomtovisit(whomToVisit);
        newRecord.setDate(date);
        recordRepository.save(newRecord);

        int id=newRecord.getRecordid();
        Request newRequest =new Request();
        //Optional<Record> record=recordRepository.findById(newRecord);

        //Optional<Record> currRecord = recordRepository.findById(id);
        //newRequest.setRequestId(newRequest.getRequestId());
        newRequest.setRecordId(id);
        newRequest.setEmployeeId(whomToVisit);
        newRequest.setVisitorId(visitorId);
        newRequest.setStatus("Waiting");
        requestRepository.save(newRequest);
        return new ResponseEntity("success", HttpStatus.OK);

    }

    @Override
    public ResponseEntity <HttpStatus> setIntime(int recordId,String time) {
        Optional<Record> record=recordRepository.findById(recordId);
        record.get().setIntime(time);
        recordRepository.save(record.get());
        return new ResponseEntity("Successfully update",HttpStatus.OK);
    }

    @Override
    public ResponseEntity <HttpStatus> setOutTime(int recordId,String time) {
        Optional<Record> record=recordRepository.findById(recordId);
        record.get().setOuttime(time);
        recordRepository.save(record.get());
        return new ResponseEntity("Successfully update",HttpStatus.OK);
    }

    @Override
    public ResponseEntity <Record> changeApprovedBy(int recordId, int approvedby) {
        Optional<Record> record=recordRepository.findByRecordid(recordId);
        record.get().setApprovedby(approvedby);
        recordRepository.save(record.get());
        return new ResponseEntity("Successfully update",HttpStatus.OK);

    }

    @Override
    public ResponseEntity <Record> updateComment(int recordId, String comment) {
        Optional<Record> record=recordRepository.findByRecordid(recordId);
        record.get().setComment(comment);
        recordRepository.save(record.get());
        return new ResponseEntity("Successfully update",HttpStatus.OK);

    }

    @Override
    public List <GetCommentsByEmployeeId> findCommentByEmployeeId(int whomToVisit) {
        List<GetCommentsByEmployeeId> getComment=new ArrayList <>();
        try {
            List <Record> records = recordRepository.findByWhomtovisitAndCommentIsNotNullAndPreapprovalIsTrue(whomToVisit);
            for(Record r:records){
                GetCommentsByEmployeeId getCommentsByEmployeeId=new GetCommentsByEmployeeId();
                getCommentsByEmployeeId.setComment(r.getComment());
                getCommentsByEmployeeId.setDate(r.getDate());
                getComment.add(getCommentsByEmployeeId);

            }
            return getComment;

        }catch (Exception e){
            return getComment;
        }
    }

//    @Override
//    public ResponseEntity <HttpStatus> setIntime(int recordid) {
//        Optional<Record> record = recordRepository.findById(recordid);
//        record.get().setIntime((Timestamp) LocalTime);
//        recordRepository.save(record.get());
//        return new ResponseEntity ("InTime Set Successfully",HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity <HttpStatus> setOutTime(int recordid) {
//        Optional<Record> record = recordRepository.findById(recordid);
//        record.get().setIntime((Timestamp) LocalTime);
//        recordRepository.save(record.get());
//        return new ResponseEntity ("OutTime Set Successfully",HttpStatus.OK);
//    }

//    @Override
//    public ResponseEntity updateUser(int userId, String username, String emailId, String jobPosition) {
//        public ResponseEntity<String> update_user(int recordid, String username,String emailId,String jobPosition){
//            try {
//                Record record = recordRepository.findById(recordid);
//                record.setName(username);
//                record.setComment(emailId);
//                record.setComment(jobDescription);
//                recordRepository.save(record);
//                return new ResponseEntity("Successfully update",HttpStatus.OK);
//            }catch (Exception e){
//                return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
//            }
//
//        }
//    }

}
