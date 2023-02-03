package com.quinbay.visitors.repository;
import java.util.*;

import com.quinbay.visitors.models.Record;
import com.quinbay.visitors.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepository extends JpaRepository<Request,Integer> {

    @Query("SELECT r FROM Request r WHERE r.status = 'Waiting'")
    List<Request> findAllByStatusWaiting();

    @Query("SELECT r FROM Request r WHERE r.status = 'Approved'")
    List<Request> findAllByStatusApproved();

    @Query("SELECT r FROM Request r WHERE r.status = 'Approved'")
    List<Request> findAllByStatusDisapproved();

    Optional<Request> findByRecordId(int id);
 //   List<Request> findById(int id);
    List<Request> findByEmployeeId(int employeeId);
    //List<Request> findByDate(String date);


    //Optional<Request> findByEmployeeid(int id);
    Optional<Request> findByRequestId(int id);
    List<Request> findByEmployeeIdAndStatus(int employeeId, String status);
    //List<Record> findByApprovedbyAndIntimeAndOuttime(int a, Object b, Object c);
    //List<Request> findByEmployeeidAndStatus(int a,String string);


}
