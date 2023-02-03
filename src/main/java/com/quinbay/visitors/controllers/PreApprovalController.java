package com.quinbay.visitors.controllers;

import com.quinbay.visitors.helpers.GetNotVisitedPreApprovers;
import com.quinbay.visitors.helpers.GetRecordsByDate;
import com.quinbay.visitors.helpers.NewPreApproval;
import com.quinbay.visitors.interfaces.PreApprovalInterface;
import com.quinbay.visitors.models.PreApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PreApprovalController {

    @Autowired
    PreApprovalInterface preApprovalInterface;

    @PostMapping("/createPreApproval")
    public ResponseEntity createPreApproval(@RequestBody NewPreApproval newPreApproval) {
        return preApprovalInterface.createPreApproval(newPreApproval);
    }

    @GetMapping("/allPreApprovals")
    public List<PreApproval> allPreApprovals(){
        return preApprovalInterface.allPreApprovals();
    }

    @GetMapping("/allNotVisitedPreApprovals")
    public List<GetNotVisitedPreApprovers> getNotVisitedPreApprovers(){
        return preApprovalInterface.getNotVisitedPreApprovers();
    }

    @GetMapping("/getPreVisitorsByDate")
    public List<GetRecordsByDate> getPreVisitorsByDate(@RequestParam String date){
        return preApprovalInterface.getPreVisitorsByDate(date);

    }

    @PutMapping("/updateVisitedStatus")
    public ResponseEntity<PreApproval>updateVisitedStatus(@RequestParam int recordId){
        return preApprovalInterface.updateVisitedStatus(recordId);
    }








//    @GetMapping("/approvalsOn/date}")
//    public ResponseEntity<PreApproval> getByDate(@RequestParam("date")String date){
//        return preApprovalInterface.getByDate(date);
//    }

}
