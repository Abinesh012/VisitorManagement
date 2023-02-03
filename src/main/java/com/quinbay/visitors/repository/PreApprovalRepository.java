package com.quinbay.visitors.repository;


import com.quinbay.visitors.models.PreApproval;
import com.quinbay.visitors.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PreApprovalRepository extends JpaRepository<PreApproval,Integer> {

    //Optional<PreApproval> getByDate(LocalDate currentDate);
    List<PreApproval> findByDate(String date);
    List<PreApproval> findByHasVisitedIsFalse();
    Optional<PreApproval> findByRecordId(int recordId);
    //List<Request> findAllByStatusDisapproved();

}
