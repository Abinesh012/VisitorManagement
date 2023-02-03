package com.quinbay.visitors.interfaces;

import com.quinbay.visitors.helpers.GetNotVisitedPreApprovers;
import com.quinbay.visitors.helpers.GetRecordsByDate;
import com.quinbay.visitors.helpers.NewPreApproval;
import com.quinbay.visitors.models.PreApproval;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PreApprovalInterface {
    List<PreApproval> allPreApprovals();

    List<GetNotVisitedPreApprovers> getNotVisitedPreApprovers();

    List<GetRecordsByDate> getPreVisitorsByDate(String date);

    ResponseEntity createPreApproval(NewPreApproval newPreApproval);


    ResponseEntity<PreApproval> updateVisitedStatus(int recordId);


    //ResponseEntity <PreApproval> getByDate(String date);
}
