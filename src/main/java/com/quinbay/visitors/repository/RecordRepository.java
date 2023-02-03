package com.quinbay.visitors.repository;

import com.quinbay.visitors.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record,Integer> {

    //Optional<Record> findByName(String name);
//    Optional<Record> findById(int name);

    List<Record> findByIntimeIsNotNullAndOuttimeIsNotNull();
    List<Record>findByIntimeIsNotNullAndOuttimeIsNull();
    List<Record>findByDate(String date);
    List<Record>findByWhomtovisitAndCommentIsNotNullAndPreapprovalIsTrue(int whomToVisit);
    Optional<Record> findByRecordid(int recordId);
    List<Record>findByWhomtovisit(int employeeId);




}
