package com.quinbay.visitors.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "preapproval")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreApproval {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int preApprovalId;
        int recordId;
        int visitorId;
        int employeeId;
        String type;
        String comment;
        String date;
        String fromTime;
        String toTime;
        boolean hasVisited;

}
