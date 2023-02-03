package com.quinbay.visitors.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPreApproval {

    int userId;
    int visitorId;
    String purpose;
    String date;
    String fromTime;
    String toTime;
    String type;
}
