package com.quinbay.visitors.interfaces;

import com.quinbay.visitors.helpers.*;
import com.quinbay.visitors.models.Request;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RequestInterface {
    //String createRequest(Request request);

    String createRequest(int visitorid, String whomtovisit, String purpose);

    List<GetbyWaiting> getByWaiting();

    List <GetCurrentvisitors> getByApproved();

//    List<Request> getByDisapproved();
//
//    Request getWaitingVisitors(int employeeid);

    ResponseEntity<Request> changeStatus(int requestid, String status);

    List <Request> getAllRequests();

    List<ReqByStatus> getRequestByStatus(int employeeid, String status);

    List<Request> getByCheckedOut();

    List<GetRecordData> getRecordData();

    List<GetRecordData> getRecordDataById(int employeeid);

    List <GetStatistics> getStatistics(String date);

    List<GetRecordData> getRecordDataByDate(String date);
}
