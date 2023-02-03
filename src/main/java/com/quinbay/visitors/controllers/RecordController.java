package com.quinbay.visitors.controllers;

import com.quinbay.visitors.helpers.GetCommentsByEmployeeId;
import com.quinbay.visitors.interfaces.RecordInterface;
import com.quinbay.visitors.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecordController {
    @Autowired
    RecordInterface recordInterface;

    @PostMapping("/createRecord")
    public ResponseEntity createRecord(@RequestParam int visitorid, @RequestParam int whomtovisit, @RequestParam String purpose,@RequestParam String date) {
        return recordInterface.createRecord(visitorid,whomtovisit,purpose,date);
    }

    @GetMapping("/allRecord")
    public List<Record> getAllRecord(){
        return recordInterface.getAllrecord();
    }

    @GetMapping("/record/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable(value = "id") Integer recordId){
        return recordInterface.getRecord(recordId);
    }

    @DeleteMapping("/deleteRecord/{id}")
    public ResponseEntity<HttpStatus> deleteRecordById(@RequestParam int recordId){
        return recordInterface.deleteRecord(recordId);
    }
    @PutMapping("/inTime")
    public ResponseEntity<HttpStatus>setIntime(@RequestParam int recordId,@RequestParam String time){
        return recordInterface.setIntime(recordId,time);
    }

    @PutMapping("/outTime")
    public ResponseEntity<HttpStatus>setOuttime(@RequestParam int recordId, @RequestParam String time){
        return recordInterface.setOutTime(recordId,time);
    }
    @PutMapping("/changeApprovedBy")
    public ResponseEntity<Record>changeApprovedBy(@RequestParam int recordId, @RequestParam int approvedBy){
        return recordInterface.changeApprovedBy(recordId,approvedBy);
    }

    @PutMapping("/updateCommentById")
    public ResponseEntity<Record>updateComment(@RequestParam int recordId, @RequestParam String comment){
        return recordInterface.updateComment(recordId,comment);
    }

    @GetMapping("/findCommentsByEmployeeId")
    public List<GetCommentsByEmployeeId>findCommentByEmployeeId(@RequestParam int whomToVisit){
        return recordInterface.findCommentByEmployeeId(whomToVisit);
    }




}
