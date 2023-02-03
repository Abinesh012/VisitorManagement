package com.quinbay.visitors.helpers;

import com.quinbay.visitors.models.PreApproval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetNotVisitedPreApprovers {

    int visitorId;
    String visitorName;
    String employeeName;
    String fromTime;
    String toTime;
    String type;
    String purpose;
    int recordId;
    int preApprovalId;
    int employeeId;

}

