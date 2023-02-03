package com.quinbay.visitors.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRecordsByDate {
    int visitorid;
    String visitorname;
    int preApprovalId;
    int employeeid;
    String empname;
    int recordid;
    String purpose;
    String type;
    String intime;
    String outtime;
    long phone;
}
