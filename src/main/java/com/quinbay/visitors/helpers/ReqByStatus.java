package com.quinbay.visitors.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqByStatus {
    int recordid;
    int requestid;
    String name;
    String purpose;
    long number;
}
