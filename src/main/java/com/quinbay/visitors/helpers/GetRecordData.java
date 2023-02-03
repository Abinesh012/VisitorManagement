package com.quinbay.visitors.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRecordData {
    int visitorid;
    String visitorname;
    int employeeid;
    String empname;
    int requestid;
    int recordid;
    String Status;
    String purpose;
    String date;
    String intime;
    String outtime;
    long phone;
}
