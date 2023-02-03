package com.quinbay.visitors.interfaces;

import com.quinbay.visitors.helpers.GetCommentsByEmployeeId;
import com.quinbay.visitors.models.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecordInterface {
    //String createRecord(Record record);
    List<Record> getAllrecord();
    ResponseEntity<Record> getRecord(int rid);
    ResponseEntity<HttpStatus> deleteRecord(int rid);

    ResponseEntity updateUser(int userId, String username, String emailId, String jobPosition);


    ResponseEntity createRecord(int visitorId, int whomToVisit, String purpose, String date);

    ResponseEntity<HttpStatus> setIntime(int recordId,String time);

    ResponseEntity<HttpStatus> setOutTime(int recordId,String time);

    ResponseEntity<Record> changeApprovedBy(int recordId, int approvedBy);

    ResponseEntity<Record> updateComment(int recordId, String comment);

    List <GetCommentsByEmployeeId> findCommentByEmployeeId(int whomToVisit);
}
