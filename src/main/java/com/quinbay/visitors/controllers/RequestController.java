package com.quinbay.visitors.controllers;

import com.quinbay.visitors.helpers.GetCurrentvisitors;
import com.quinbay.visitors.helpers.GetRecordData;
import com.quinbay.visitors.interfaces.RequestInterface;
import com.quinbay.visitors.models.Request;
import com.quinbay.visitors.helpers.GetbyWaiting;
import com.quinbay.visitors.helpers.ReqByStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    RequestInterface requestInterface;


    @GetMapping("/allRequests")
    public List<Request> getAllRequests(){
        return requestInterface.getAllRequests();
    }

    @GetMapping("/getWaiting")
    public List<GetbyWaiting> getbyWaiting(){
        return requestInterface.getByWaiting();
    }

    @GetMapping("/getApproved")
    public List<GetCurrentvisitors> getbyApproved(){
        return requestInterface.getByApproved();
    }

    @GetMapping("/getCheckedOut")
    public List<Request> getbyCheckedOut(){
        return requestInterface.getByCheckedOut();
    }

//    @GetMapping("/getDisapproved")
//    public List<Request> getbyDisapproved(){
//        return requestInterface.getByDisapproved();
//    }
//
//    @GetMapping("/getWaitingVisitors")
//    public Request RequestwaitingVisitors(@RequestParam int employeeid){
//        return requestInterface.getWaitingVisitors(employeeid);
//    }
    @PutMapping("/changeStatus")
    public ResponseEntity<Request>changeStatus(@RequestParam int requestid, @RequestParam String status){
        return requestInterface.changeStatus(requestid,status);
    }

    @GetMapping("/getRequestsByStatus")
    public List<ReqByStatus>getRequestByStatus(@RequestParam int employeeid, @RequestParam String status){
        return requestInterface.getRequestByStatus(employeeid,status);
    }
    @GetMapping("/recordData")
    public List<GetRecordData>getRecordData(){
        return  requestInterface.getRecordData();
    }

    @GetMapping("/recordDataById")
    public List<GetRecordData>getRecordDataById(@RequestParam int employeeid ){
        return  requestInterface.getRecordDataById(employeeid);
    }

    @GetMapping("/recordDataByDate")
    public List<GetRecordData>getRecordDataByDate(@RequestParam String date ){
        return  requestInterface.getRecordDataByDate(date);
    }

//    @GetMapping("/recordsByDate")
//    public List<>


}
